package com.ocean.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.service.ReservationService;

@WebServlet("/UpdateReservationServlet")
public class UpdateReservationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        int id = Integer.parseInt(request.getParameter("id"));
        String guestName = request.getParameter("guestName");
        String address = request.getParameter("address");
        String contactNo = request.getParameter("contactNo");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");

        // Call service method
        boolean isUpdated = ReservationService.updateReservation(
                id, guestName, address, contactNo,
                roomId, checkInDate, checkOutDate
        );

        // Redirect
        if (isUpdated) {
            response.sendRedirect("ViewReservationServlet?update=success");
        } else {
            response.sendRedirect("ViewReservationServlet?update=error");
        }
    }
}