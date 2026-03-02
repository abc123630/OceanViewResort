package com.ocean.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ocean.model.Room;
import com.ocean.model.Reservation;
import com.ocean.util.DBConnection;

public class ReservationService {

    
    // GET AVAILABLE ROOM BY TYPE AND DATES
    
    public static Room getAvailableRoom(String roomType,
                                        String checkIn,
                                        String checkOut) {

        Room room = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT r.* FROM rooms r "
                    + "WHERE r.room_type = ? "
                    + "AND r.id NOT IN ( "
                    + "   SELECT room_id FROM reservations "
                    + "   WHERE NOT (check_out_date <= ? OR check_in_date >= ?) "
                    + ") LIMIT 1";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, roomType);
            ps.setDate(2, java.sql.Date.valueOf(checkIn));
            ps.setDate(3, java.sql.Date.valueOf(checkOut));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                room = new Room(
                        rs.getInt("id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getDouble("rate_per_night")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return room;
    }

   
    // CALCULATE TOTAL PRICE (Before booking)
    
    public static double calculateTotal(Room room,
                                        String checkIn,
                                        String checkOut) {

        if (room == null) return 0;

        LocalDate in = LocalDate.parse(checkIn);
        LocalDate out = LocalDate.parse(checkOut);

        long nights = ChronoUnit.DAYS.between(in, out);

        return nights * room.getRatePerNight();
    }

    
    // ADD RESERVATION
   
    public static boolean addReservation(String guestName,
                                         String address,
                                         String contactNo,
                                         int roomId,
                                         String checkInDate,
                                         String checkOutDate) {

        boolean isSuccess = false;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "INSERT INTO reservations "
                    + "(guest_name, address, contact_no, room_id, check_in_date, check_out_date) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, guestName);
            ps.setString(2, address);
            ps.setString(3, contactNo);
            ps.setInt(4, roomId);

            // ⭐ FIXED DATE ISSUE
            ps.setDate(5, java.sql.Date.valueOf(checkInDate));
            ps.setDate(6, java.sql.Date.valueOf(checkOutDate));

            isSuccess = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    
    // GET ALL RESERVATIONS
   
    public static List<Reservation> getAllReservations() {

        List<Reservation> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM reservations";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Reservation r = new Reservation(
                        rs.getInt("id"),
                        rs.getString("guest_name"),
                        rs.getString("address"),
                        rs.getString("contact_no"),
                        rs.getInt("room_id"),
                        rs.getString("check_in_date"),
                        rs.getString("check_out_date")
                );

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
    // SEARCH RESERVATIONS
    
    public static List<Reservation> searchReservations(String keyword) {

        List<Reservation> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM reservations "
                    + "WHERE guest_name LIKE ? OR contact_no LIKE ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Reservation r = new Reservation(
                        rs.getInt("id"),
                        rs.getString("guest_name"),
                        rs.getString("address"),
                        rs.getString("contact_no"),
                        rs.getInt("room_id"),
                        rs.getString("check_in_date"),
                        rs.getString("check_out_date")
                );

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
    // GET RESERVATION BY ID
 
    public static Reservation getReservationById(int id) {

        Reservation r = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM reservations WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                r = new Reservation(
                        rs.getInt("id"),
                        rs.getString("guest_name"),
                        rs.getString("address"),
                        rs.getString("contact_no"),
                        rs.getInt("room_id"),
                        rs.getString("check_in_date"),
                        rs.getString("check_out_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    
    // GET RESERVATION WITH ROOM DETAILS (FOR BILL)
   
    public static Reservation getReservationWithRoom(int id) {

        Reservation r = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT res.*, rm.room_number, rm.room_type, rm.rate_per_night "
                    + "FROM reservations res "
                    + "JOIN rooms rm ON res.room_id = rm.id "
                    + "WHERE res.id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                r = new Reservation(
                        rs.getInt("id"),
                        rs.getString("guest_name"),
                        rs.getString("address"),
                        rs.getString("contact_no"),
                        rs.getInt("room_id"),
                        rs.getString("check_in_date"),
                        rs.getString("check_out_date")
                );

                // ⭐ Room details
                r.setRoomNumber(rs.getString("room_number"));
                r.setRoomType(rs.getString("room_type"));
                r.setRatePerNight(rs.getDouble("rate_per_night"));
            }
            
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    
    // CALCULATE BILL
   
    public static double calculateBill(Reservation r) {

        LocalDate in = LocalDate.parse(r.getCheckInDate());
        LocalDate out = LocalDate.parse(r.getCheckOutDate());

        long nights = ChronoUnit.DAYS.between(in, out);

        return nights * r.getRatePerNight();
    }

    
    // UPDATE RESERVATION
    
    public static boolean updateReservation(int id,
                                            String guestName,
                                            String address,
                                            String contactNo,
                                            int roomId,
                                            String checkInDate,
                                            String checkOutDate) {

        boolean isSuccess = false;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE reservations SET "
                    + "guest_name=?, address=?, contact_no=?, "
                    + "room_id=?, check_in_date=?, check_out_date=? "
                    + "WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, guestName);
            ps.setString(2, address);
            ps.setString(3, contactNo);
            ps.setInt(4, roomId);

            ps.setDate(5, java.sql.Date.valueOf(checkInDate));
            ps.setDate(6, java.sql.Date.valueOf(checkOutDate));

            ps.setInt(7, id);

            isSuccess = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    
    // DELETE RESERVATION
    
    public static boolean deleteReservation(int id) {

        boolean isSuccess = false;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM reservations WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            isSuccess = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }



//GET ALL RESERVATIONS WITH ROOM DETAILS (FOR BILL DROPDOWN)

public static List<Reservation> getAllReservationsWithRoom() {

 List<Reservation> list = new ArrayList<>();

 try (Connection con = DBConnection.getConnection()) {

     String sql = "SELECT res.*, rm.room_number, rm.room_type, rm.rate_per_night "
             + "FROM reservations res "
             + "JOIN rooms rm ON res.room_id = rm.id";

     PreparedStatement ps = con.prepareStatement(sql);
     ResultSet rs = ps.executeQuery();

     while (rs.next()) {

         Reservation r = new Reservation(
                 rs.getInt("id"),
                 rs.getString("guest_name"),
                 rs.getString("address"),
                 rs.getString("contact_no"),
                 rs.getInt("room_id"),
                 rs.getString("check_in_date"),
                 rs.getString("check_out_date")
         );

         // ⭐ Add room details
         r.setRoomNumber(rs.getString("room_number"));
         r.setRoomType(rs.getString("room_type"));
         r.setRatePerNight(rs.getDouble("rate_per_night"));

         list.add(r);
     }

 } catch (Exception e) {
     e.printStackTrace();
 }

 return list;
}
}


