package com.ocean.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.model.Room;
import com.ocean.service.RoomService;

@WebServlet("/ReservationFormServlet")
public class ReservationFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get all rooms from database
        List<Room> roomList = RoomService.getAllRooms();

        request.setAttribute("roomList", roomList);

        // Forward to JSP
        RequestDispatcher rd = request.getRequestDispatcher("addReservation.jsp");
        rd.forward(request, response);
    }
}

