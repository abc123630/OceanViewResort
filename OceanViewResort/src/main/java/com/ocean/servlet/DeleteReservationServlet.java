package com.ocean.servlet;


import java.io.IOException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.service.ReservationService;

@WebServlet("/DeleteReservationServlet")
public class DeleteReservationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ReservationService.deleteReservation(id);

        response.sendRedirect("ViewReservationServlet");
        
       
        }
    }
  
