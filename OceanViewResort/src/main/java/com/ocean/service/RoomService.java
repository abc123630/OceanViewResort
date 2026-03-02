package com.ocean.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ocean.model.Room;
import com.ocean.util.DBConnection;

public class RoomService {

    // Get all rooms
    public static List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM rooms";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Room room = new Room(
                        rs.getInt("id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getDouble("rate_per_night")
                );

                rooms.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // Get rooms by type
    public static List<Room> getRoomsByType(String roomType) {

        List<Room> rooms = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM rooms WHERE room_type = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomType);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Room room = new Room(
                        rs.getInt("id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getDouble("rate_per_night")
                );

                rooms.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // Get room by ID
    public static Room getRoomById(int id) {

        Room room = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM rooms WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

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
}