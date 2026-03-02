package com.ocean.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ocean.service.LoginService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = LoginService.authenticate(username, password);
        
     // DEBUG PRINT (ADD HERE)
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Login result: " + isValid);
        
        

        if ("admin".equals(username) && "admin123".equals(password)) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=true");
        }


        Cookie userCookie = new Cookie("username", username);
        userCookie.setMaxAge(60 * 60 * 24); // 1 day
        response.addCookie(userCookie);

    }
    
    
}



    

