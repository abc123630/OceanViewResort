package com.ocean.servlet;

import java.io.IOException;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.model.Reservation;
import com.ocean.service.ReservationService;

@WebServlet("/calculateBill")
public class CalculateBillServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Get reservation ID from URL
        int id = Integer.parseInt(request.getParameter("id"));

        // Get reservation WITH room details
        Reservation r = ReservationService.getReservationWithRoom(id);

        if (r == null) {
            request.setAttribute("message", "Reservation not found");
            RequestDispatcher rd = request.getRequestDispatcher("viewReservations.jsp");
            rd.forward(request, response);
            return;
        }

        // Calculate number of nights
        LocalDate in = LocalDate.parse(r.getCheckInDate());
        LocalDate out = LocalDate.parse(r.getCheckOutDate());

        long nights = ChronoUnit.DAYS.between(in, out);

        // Calculate total amount
        double total = nights * r.getRatePerNight();

        // 5️⃣ Send data to JSP
        request.setAttribute("reservation", r);
        request.setAttribute("nights", nights);
        request.setAttribute("total", total);

        // Forward to bill page
        RequestDispatcher rd = request.getRequestDispatcher("bill.jsp");
        rd.forward(request, response);
    }
}