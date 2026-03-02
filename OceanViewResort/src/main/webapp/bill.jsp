<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ocean View Resort - Bill</title>
    <link rel="stylesheet" href="css/bill.css">
</head>
<body>

<div class="bill-container">

<h2 class="hotel-name">Ocean View Resort</h2>
<hr>

<div class="section">
    <p><strong>Guest Name:</strong> ${reservation.guestName}</p>
    <p><strong>Address:</strong> ${reservation.address}</p>
    <p><strong>Contact:</strong> ${reservation.contactNo}</p>
</div>

<div class="section">
    <p><strong>Room Number:</strong> ${reservation.roomNumber}</p>
    <p><strong>Room Type:</strong> ${reservation.roomType}</p>
    <p><strong>Rate Per Night:</strong> Rs. ${reservation.ratePerNight}</p>
    <p><strong>Check In:</strong> ${reservation.checkInDate}</p>
    <p><strong>Check Out:</strong> ${reservation.checkOutDate}</p>
</div>

<hr>

<h3 class="total">Total Amount: Rs. ${total}</h3>

</div>

</body>
</html>
