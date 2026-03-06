package com.ocean.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class LoginServletTest {

	@Test
	void testValidLoginRedirectsToDashboard() throws ServletException, IOException {

	    LoginServlet servlet = new LoginServlet();

	    HttpServletRequest request = mock(HttpServletRequest.class);
	    HttpServletResponse response = mock(HttpServletResponse.class);

	    when(request.getParameter("username")).thenReturn("admin");
	    when(request.getParameter("password")).thenReturn("admin123");

	    ArgumentCaptor<String> redirectCaptor = ArgumentCaptor.forClass(String.class);

	    servlet.doPost(request, response);

	    verify(response).sendRedirect(redirectCaptor.capture());
	    assertEquals("dashboard.jsp", redirectCaptor.getValue());
	}
}	