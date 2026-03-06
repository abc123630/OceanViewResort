package com.ocean.servlet;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

class LoginServletTest {

    @Test
    void testInvalidLoginRedirectsToError() throws IOException {
        // Arrange
        LoginServlet servlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("123");

        ArgumentCaptor<String> redirectCaptor = ArgumentCaptor.forClass(String.class);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect(redirectCaptor.capture());
        assertEquals("login.jsp?error=true", redirectCaptor.getValue());
    }
}