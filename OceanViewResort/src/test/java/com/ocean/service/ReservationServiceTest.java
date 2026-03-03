
package com.ocean.service;

import com.ocean.model.Reservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

	@Test
	void testCalculateBill() {

	    Reservation r = new Reservation(
	            1,
	            "John",
	            "Colombo",
	            "0771234567",
	            101,
	            "2026-03-01",
	            "2026-03-05"
	    );

	    r.setRatePerNight(100.0);

	    double bill = ReservationService.calculateBill(r);

	    assertEquals(400.0, bill, 0.001);
	}
}	
