package com.ocean.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.ocean.model.Reservation;
import com.ocean.service.ReservationService;

@WebServlet("/BillServlet") 
public class BillServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("viewReservation.jsp");
            return;
        }

        int id = Integer.parseInt(idStr);

        // Fetch reservation with room details
        Reservation res = ReservationService.getReservationWithRoom(id);

        if (res == null) {
            request.setAttribute("message", "Reservation not found");
            RequestDispatcher rd = request.getRequestDispatcher("viewReservation.jsp");
            rd.forward(request, response);
            return;
        }

        // Calculate total nights
        java.time.LocalDate checkIn = java.time.LocalDate.parse(res.getCheckInDate());
        java.time.LocalDate checkOut = java.time.LocalDate.parse(res.getCheckOutDate());
        long nights = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights == 0) nights = 1;

        double total = nights * res.getRatePerNight();

        // Set attributes for JSP
        request.setAttribute("reservation", res);
        request.setAttribute("nights", nights);
        request.setAttribute("total", total);

        // Forward to bill.jsp
        RequestDispatcher rd = request.getRequestDispatcher("bill.jsp");
        rd.forward(request, response);
    }
}
