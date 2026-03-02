<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ocean.model.Reservation" %>
<%@ page import="com.ocean.service.ReservationService" %>

<!DOCTYPE html>
<html>
<head>
    <title>Print Bill</title>
    <link rel="stylesheet" href="css/printBill.css">
    <script>
        window.onload = function() {
            window.print();
        }
    </script>
</head>
<body>
<div class="container">
<%
String reservationIdStr = request.getParameter("reservationId");
if(reservationIdStr != null && !reservationIdStr.isEmpty()) {
    int reservationId = Integer.parseInt(reservationIdStr);
    

    // fetch reservation WITH room details
    Reservation res = ReservationService.getReservationWithRoom(reservationId);

    if(res != null) {
        java.time.LocalDate checkIn = java.time.LocalDate.parse(res.getCheckInDate());
        java.time.LocalDate checkOut = java.time.LocalDate.parse(res.getCheckOutDate());
        long days = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        if(days == 0) days = 1;

        double total = days * res.getRatePerNight();
%>

<h2>Hotel Bill</h2>
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

<%
        } else {
%>
    <p style="color:red;">Reservation not found!</p>
<%
        }
    }
%>
</div>
</body>
</html>