package com.ocean.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.model.Room;
import com.ocean.service.ReservationService;

@WebServlet("/addReservation")
public class AddReservationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String guestName = request.getParameter("guestName");
        String address = request.getParameter("address");
        String contactNo = request.getParameter("contactNo");
        String roomType = request.getParameter("roomType");
        String checkIn = request.getParameter("checkInDate");
        String checkOut = request.getParameter("checkOutDate");

        // Basic validation
        if (checkOut.compareTo(checkIn) <= 0) {
            response.sendRedirect("addReservation.jsp?error=date_invalid");
            return;
        }

        // Find available room
        Room room = ReservationService.getAvailableRoom(roomType, checkIn, checkOut);

        if (room == null) {
            response.sendRedirect("addReservation.jsp?error=room_unavailable");
            return;
        }

        // Pass room.getId() instead of room object
        boolean isAdded = ReservationService.addReservation(
                guestName, address, contactNo,
                room.getId(),
                checkIn, checkOut);

        if (isAdded) {
            response.sendRedirect("addReservation.jsp?success=true");
        } else {
            response.sendRedirect("addReservation.jsp?error=true");
        }
    }
}