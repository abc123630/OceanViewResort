<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ocean.model.Reservation" %>
<%@ page import="com.ocean.service.ReservationService" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Reservations</title>
    <link rel="stylesheet" href="css/viewReservation.css">
</head>
<body>

<!-- HEADER WITH NAVIGATION -->
<header class="header">

    <div class="header-container">

        <!-- Logo + Hotel Name -->
        <div class="logo">
            <img src="images/logo.jpg" alt="Ocean View Resort">
            <span>Ocean View Resort</span>
        </div>

        <!-- Navigation Menu -->
        <nav class="nav">
        <span class="h">
            <a href="dashboard.jsp">Home</a></span>
            <a href="addReservation.jsp">Add Reservation</a>
            <a href="ViewReservationServlet">View Reservations</a>
            <a href="calculateBill.jsp">Billing</a>
            <a href="help.jsp">Help</a>
        </nav>

        <!-- Logout Button -->
        <a href="login.jsp" class="logout">Logout</a>

    </div>
    </header>


<div class="main-content">
<div class="container">
<h2>Reservation List</h2>

<!-- SEARCH -->
<div class="search-box">
    <form action="ViewReservationServlet" method="get">
        <input type="text" name="keyword"
               placeholder="Search by name or contact">
        <button type="submit">Search</button>
    </form>
</div>

<table>
<tr>
    <th>ID</th>
    <th>Guest Name</th>
    <th>Address</th>
    <th>Contact</th>
    <th>Room Number</th>
    <th>Room Type</th>
    <th>Check In</th>
    <th>Check Out</th>
    
    <th>Actions</th>
</tr>

<%
    List<Reservation> reservations = ReservationService.getAllReservationsWithRoom();
    if(reservations != null && !reservations.isEmpty()) {
        for(Reservation r : reservations) {
%>
<tr>
    <td><%= r.getId() %></td>
    <td><%= r.getGuestName() %></td>
    <td><%= r.getAddress() %></td>
    <td><%= r.getContactNo() %></td>
    <td><%= r.getRoomNumber() %></td>
    <td><%= r.getRoomType() %></td>
    <td><%= r.getCheckInDate() %></td>
    <td><%= r.getCheckOutDate() %></td>
    
   <td>
        <a class="edit"
           href="EditReservationServlet?id=<%= r.getId() %>">Edit</a>

        <a class="delete"
           href="DeleteReservationServlet?id=<%= r.getId() %>"
           onclick="return confirm('Delete this reservation?');">
           Delete
        </a>

        <a class="bill"
           href="BillServlet?id=<%= r.getId() %>">Bill</a>
    </td>
</tr>
<%
        }
    } else {
%>
<tr>
    <td colspan="9" style="text-align:center;">No reservations found.</td>
</tr>
<%
    }
%>
</table>
</div>
</div>

<!-- Footer -->
<footer class="footer">
    <p>&copy; 2026 Ocean View Resort. All rights reserved.</p>
    <p>Email: <a href="mailto:support@oceanviewresort.com">support@oceanviewresort.com</a></p>
</footer>


</body>
</html>
