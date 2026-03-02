package com.ocean.servlet;


import java.io.IOException;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.model.Reservation;
import com.ocean.service.ReservationService;

@WebServlet("/EditReservationServlet")
public class EditReservationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Reservation reservation =
                ReservationService.getReservationById(id);

        request.setAttribute("reservation", reservation);

        RequestDispatcher rd =
                request.getRequestDispatcher("editReservation.jsp");
        rd.forward(request, response);
    }
}