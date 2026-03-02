<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <title>Ocean View Resort - Help</title>
    <link rel="stylesheet" href="css/help.css">
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

<div class="help-container">

    <h1>Ocean View Resort Management System</h1>
    <h2>User Help Guide</h2>

    <!-- System Overview -->
    <div class="section">
        <h3>System Overview</h3>
        <p>
            This system helps staff manage room reservations,
            guest information, room allocation, and billing
            efficiently at Ocean View Resort.
        </p>
    </div>

    <!-- Add Reservation -->
    <div class="section">
        <h3>Add Reservation</h3>
        <ol>
            <li>Go to <b>Add Reservation</b> page</li>
            <li>Enter guest name, address, and contact number</li>
            <li>Select room type</li>
            <li>Choose check-in and check-out dates</li>
            <li>Click <b>Submit</b> to save</li>
        </ol>
    </div>

    <!-- View Reservation -->
    <div class="section">
        <h3>View Reservations</h3>
        <p>
            Displays all reservations including guest details,
            room number, room type, and booking dates.
        </p>
    </div>

    <!-- Edit Reservation -->
    <div class="section">
        <h3>Edit Reservation</h3>
        <ol>
            <li>Open Reservation List</li>
            <li>Click <b>Edit</b> next to the reservation</li>
            <li>Modify required details</li>
            <li>Click <b>Update</b></li>
        </ol>
    </div>

    <!-- Delete Reservation -->
    <div class="section">
        <h3>Delete Reservation</h3>
        <ol>
            <li>Go to Reservation List</li>
            <li>Click <b>Delete</b></li>
            <li>Confirm deletion</li>
        </ol>
    </div>

    <!-- Billing -->
    <div class="section">
        <h3>Generate Bill</h3>
        <ol>
            <li>Open Reservation List</li>
            <li>Click <b>Bill</b></li>
            <li>The system calculates total cost automatically</li>
            <li>Print or save the invoice</li>
        </ol>
    </div>

    <!-- Search -->
    <div class="section">
        <h3>Search Reservations</h3>
        <p>
            Use the search box to find reservations by guest name
            or contact number.
        </p>
    </div>

    <!-- Support -->
    <div class="section support">
        <h3>Support</h3>
        <p>
            For technical assistance, please contact the system administrator.
        </p>
        <p><b>Email:</b>
         <a href="mailto:support@oceanviewresort.com">
           support@oceanviewresort.com
           </a>
           </p>
        <p><b>Phone:</b> +94 77 123 4567</p>
        <p><b>Working Hours:</b> 8:00 AM — 8:00 PM</p>
    </div>

    <div class="footer1">
        Version 1.0 — Ocean View Resort Management System
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
    