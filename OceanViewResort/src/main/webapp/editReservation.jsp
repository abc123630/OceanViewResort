<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.ocean.model.Reservation" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Reservation</title>
    <link rel="stylesheet" href="css/reservation.css">
</head>
<body>

<div class="container">

<h2>Edit Reservation</h2>

<%
Reservation r = (Reservation) request.getAttribute("reservation");

if (r != null) {
%>

<form action="UpdateReservationServlet" method="post">

    <!-- Hidden ID -->
    <input type="hidden" name="id" value="<%= r.getId() %>">

    Guest Name:
    <input type="text" name="guestName"
           value="<%= r.getGuestName() %>" required><br>

    Address:
    <input type="text" name="address"
           value="<%= r.getAddress() %>"><br>

    Contact No:
    <input type="text" name="contactNo"
           value="<%= r.getContactNo() %>"><br>

    Room ID:
    <select name="roomId">
    <option value="1">101 - Single</option>
    <option value="2">102 - Single</option>
    <option value="3">103 - Single</option>
    <option value="4">201 - Double</option>
    <option value="5">202 - Double</option>
    <option value="6">301 - Family</option>
    <option value="7">302 - Family</option>
    <option value="8">401 - Suite</option>
    </select>
    
  
    Check In:
    <input type="date" name="checkInDate"
           value="<%= r.getCheckInDate() %>" required><br>

    Check Out:
    <input type="date" name="checkOutDate"
           value="<%= r.getCheckOutDate() %>" required><br>

    <button type="submit">Update Reservation</button>

</form>

<%
} else {
%>

<p style="color:red;">Reservation not found.</p>

<%
}
%>

<br>
<a href="ViewReservationServlet">Back to Reservation List</a>

</div>

</body>
</html>