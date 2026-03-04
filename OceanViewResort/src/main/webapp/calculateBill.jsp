<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ocean.model.Reservation" %>
<%@ page import="com.ocean.service.ReservationService" %>

<!DOCTYPE html>
<html>
<head>
    <title>Calculate Bill</title>
    <link rel="stylesheet" href="css/calculateBill.css">
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
    <h2>Calculate Reservation Bill</h2>

    <form method="get" action="calculateBill.jsp">
        <label for="reservationId">Select Reservation:</label>
        <select name="reservationId" id="reservationId" required>
            <option value="">-- Select Reservation --</option>
            <%
                // Fetch all reservations for dropdown
                List<Reservation> reservations = ReservationService.getAllReservationsWithRoom();
                if(reservations != null) {
                    for(Reservation r : reservations) {
            %>
               <option value="<%= r.getId() %>"
                    <%= request.getParameter("reservationId") != null && request.getParameter("reservationId").equals(String.valueOf(r.getId())) ? "selected" : "" %>>
                    <%= r.getGuestName() %> - <%= r.getRoomType() %> (Room <%= r.getRoomNumber() %>)
                </option>
            <%
                    }
                }
            %>
        </select>

        <button type="submit">Calculate Bill</button>
    </form>

    <%
        String selectedIdStr = request.getParameter("reservationId");
        if(selectedIdStr != null && !selectedIdStr.isEmpty()) {
            int reservationId = Integer.parseInt(selectedIdStr);
            Reservation res = ReservationService.getReservationWithRoom(reservationId);

            if(res != null) {
                // Calculate stay duration in days
                java.time.LocalDate checkIn = java.time.LocalDate.parse(res.getCheckInDate());
                java.time.LocalDate checkOut = java.time.LocalDate.parse(res.getCheckOutDate());
                long days = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
                if(days == 0) days = 1; // minimum 1 day

                double total = days * res.getRatePerNight();
    %>

    <div class="result-box">
        <h3>Bill Details</h3>
        <table class="bill-table">
            <tr><th>Guest Name</th><td><%= res.getGuestName() %></td></tr>
            <tr><th>Room Number</th><td><%= res.getRoomNumber() %></td></tr>
            <tr><th>Room Type</th><td><%= res.getRoomType() %></td></tr>
            <tr><th>Check-In</th><td><%= res.getCheckInDate() %></td></tr>
            <tr><th>Check-Out</th><td><%= res.getCheckOutDate() %></td></tr>
            <tr><th>Rate per Night</th><td>Rs.<%= res.getRatePerNight() %></td></tr>
            <tr><th>Total Nights</th><td><%= days %></td></tr>
        </table>

        <p class="total-amount">Total Amount: Rs.<%= total %></p>

        <form action="printBill.jsp" method="get" target="_blank">
            <input type="hidden" name="reservationId" value="<%= res.getId() %>">
            <button type="submit">Print Bill</button>
        </form>
    </div>

    <%
            } else {
    %>
        <p style="color:red;">Reservation not found!</p>
    <%
            }
        }
    %>
</div>
</div>

<!-- Footer -->
<footer class="footer">
    <p>&copy; 2026 Ocean View Resort. All rights reserved.</p>
    <p>Email: <a href="mailto:support@oceanviewresort.com">support@oceanviewresort.com</a></p>
</footer>


</body>
</html>