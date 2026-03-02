<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Hotel Reservation</title>
    <link rel="stylesheet" href="css/reservation.css">
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

    <h2>Book a Room</h2>

    <form action="addReservation" method="post">

        <label>Guest Name</label>
        <input type="text" name="guestName" required>

        <label>Address</label>
        <input type="text" name="address">

        <label>Contact Number</label>
        <input type="text" name="contactNo" required>

        <label>Room Type</label>
        <select name="roomType" required>
            <option value="">-- Select --</option>
            <option value="Single">Single</option>
            <option value="Double">Double</option>
            <option value="Family">Family</option>
            <option value="Suite">Suite</option>
        </select>

        <label>Check-In Date</label>
        <input type="date" name="checkInDate" required>

        <label>Check-Out Date</label>
        <input type="date" name="checkOutDate" required>

        <button type="submit">Confirm Booking</button>

    </form>

    <!-- Messages -->

    <%
        String error = request.getParameter("error");

        if ("room_unavailable".equals(error)) {
    %>
        <div class="error">No rooms available for selected dates.</div>
    <%
        } else if ("date_invalid".equals(error)) {
    %>
        <div class="error">Check-out date must be after check-in date.</div>
    <%
        } else if ("true".equals(error)) {
    %>
        <div class="error">Something went wrong. Try again.</div>
    <%
        }

        if ("true".equals(request.getParameter("success"))) {
    %>
        <div class="success">Reservation successfully created!</div>
    <%
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