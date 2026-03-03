package com.ocean.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ReservationTest {

    @Test
    void testGuestName() {

        Reservation reservation = new Reservation(
                1,
                "John",
                "Colombo",
                "0771234567",
                101,
                "2026-03-01",
                "2026-03-03"
        );

     // Correct expected value
        assertEquals("John", reservation.getGuestName());
    }
}