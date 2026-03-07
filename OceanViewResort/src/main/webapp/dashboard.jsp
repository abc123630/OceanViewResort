
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ocean View Resort - Dashboard</title>
<link rel="stylesheet" href="css/dashboard.css">

   
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
        <h class="h">
            <a href="dashboard.jsp">Home</a></h>
            <a href="addReservation.jsp">Add Reservation</a>
            <a href="ViewReservationServlet">View Reservations</a>
            <a href="calculateBill.jsp">Billing</a>
            <a href="help.jsp">Help</a>
        </nav>

        <!-- Logout Button -->
        <a href="login.jsp" class="logout">Logout</a>

    </div>

</header>


<div class="welcome-box">
    <h2>Welcome to Ocean View Resort</h2>

    <%
        String user = (String) session.getAttribute("username");

        if(user != null){
    %>
        <p>Hello, <b><%= user %></b> 👋</p>
    <%
        } else {
    %>
        <p>Hello, Guest </p>
    <%
        }
    %>

    <p>Manage reservations, billing, and guest services efficiently.</p>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {

    function updateDateTime() {
        const now = new Date();

        // Date format
        const dateOptions = { 
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        };

        document.getElementById("todayDate").innerHTML =
            now.toLocaleDateString("en-US", dateOptions);

        // Time format
        document.getElementById("liveClock").innerHTML =
            now.toLocaleTimeString();
    }

    updateDateTime();
    setInterval(updateDateTime, 1000); // Update every second
});
</script>

<div class="main-content">
<div class="calendar-box">
    <h3>Today</h3>
    <p id="todayDate"></p>
    <h3>Current Time</h3>
    <p id="liveClock"></p>
</div>

<div class="rooms-section">
    <h2>Available Room Types</h2>
    <div class="rooms-container">
    
       <div class="r1">
        <div class="room-card">
            <img src="images/single.jpg" alt="Single Room">
            <h3>Single Room</h3>
            <p>Price: Rs.5000 / Night</p>
            <p>Comfortable room for one guest</p>
        </div>
        </div>
        
        </div>

       
        <div class="room-card">
            <img src="images/double.jpg" alt="Double Room">
            <h3>Double Room</h3>
            <p>Price: Rs.8000 / Night</p>
            <p>Perfect for two guests comfortably</p>
        </div>
        

       
        <div class="room-card">
            <img src="images/family.jpg" alt="Family Room">
            <h3>Family Room</h3>
            <p>Price: Rs.12000 / Night</p>
            <p>Spacious room for families comfortably</p>
        </div>
      
        
      
        <div class="room-card">
            <img src="images/suite.jpg" alt="Suite">
            <h3>Suite</h3>
            <p>Price: Rs.20000 / Night</p>
            <p>Luxury suite with premium facilities</p>
        </div>
     

    </div>
</div>





<!-- Footer -->
<footer class="footer">
    <p>&copy; 2026 Ocean View Resort. All rights reserved.</p>
    <p>Email: <a href="mailto:support@oceanviewresort.com">support@oceanviewresort.com</a></p>
</footer>

</body>
</html>
