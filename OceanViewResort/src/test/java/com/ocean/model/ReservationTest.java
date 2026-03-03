package com.ocean.model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ReservationTest {

    @Test
    void testSetAndGetGuestName() {
        Reservation reservation = new Reservation();
        reservation.setGuestName("John");

        assertEquals("John", reservation.getGuestName());
    }
}