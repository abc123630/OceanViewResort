function validateForm() {

    var username = document.getElementsByName("username")[0].value;
    var password = document.getElementsByName("password")[0].value;

    if (username === "" || password === "") {
        alert("All fields are required!");
        return false;
    }

    return true;
}


// Simple client-side validation for reservation form
function validateReservationForm() {
    var guestName = document.forms["reservationForm"]["guestName"].value;
    var contactNo = document.forms["reservationForm"]["contactNo"].value;
    var checkIn = document.forms["reservationForm"]["checkInDate"].value;
    var checkOut = document.forms["reservationForm"]["checkOutDate"].value;

    if (guestName.trim() === "") {
        alert("Please enter guest name");
        return false;
    }

    if (contactNo.trim() !== "" && !/^\d+$/.test(contactNo)) {
        alert("Contact number must be digits only");
        return false;
    }

    if (checkIn === "" || checkOut === "") {
        alert("Please select check-in and check-out dates");
        return false;
    }

    if (checkOut < checkIn) {
        alert("Check-out date cannot be before check-in date");
        return false;
    }

    return true;
}
