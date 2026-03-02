package com.ocean.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.ocean.service.ReservationService;

public class ReservationServiceTest {

	    @Test
	    void testAddReservationSuccess() {

	        boolean result = ReservationService.addReservation(
	                "Test Guest",
	                "Colombo",
	                "0771234567",
	                1,              // use existing roomId from DB
	                "2026-03-05",
	                "2026-03-06"
	        );

	        assertTrue(result);
	    }
	}


