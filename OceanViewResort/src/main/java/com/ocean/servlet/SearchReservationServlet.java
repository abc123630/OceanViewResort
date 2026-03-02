package com.ocean.servlet;


import java.io.IOException;


import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.model.Reservation;
import com.ocean.service.ReservationService;

@WebServlet("/SearchReservationServlet")
public class SearchReservationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        List<Reservation> list =
                ReservationService.searchReservations(keyword);

        request.setAttribute("reservationList", list);

        RequestDispatcher rd =
                request.getRequestDispatcher("viewReservation.jsp");
        rd.forward(request, response);
    }
}