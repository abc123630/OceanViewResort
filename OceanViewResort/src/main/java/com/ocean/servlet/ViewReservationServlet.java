package com.ocean.servlet;

import java.io.IOException;


import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.model.Reservation;
import com.ocean.service.ReservationService;

@WebServlet("/ViewReservationServlet")
public class ViewReservationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        List<Reservation> reservationList;

        // IF search box empty → show ALL
        if (keyword == null || keyword.trim().isEmpty()) {
            reservationList = ReservationService.getAllReservations();
        } else {
            // IF search text entered → filter
            reservationList = ReservationService.searchReservations(keyword);
        }

        request.setAttribute("reservationList", reservationList);

        RequestDispatcher rd =
                request.getRequestDispatcher("viewReservation.jsp");
        rd.forward(request, response);
        
        
    }
}