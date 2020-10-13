package com.capgemini.hotelReservation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HotelReservationTest {

	@Test
	public void givenHotelDetailsShouldAddHotelToMyHotelList() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel(new Hotel("Lakewood", 110));
		int size = hotelReservation.getMyHotelList().size();
		assertEquals(1, size);
	}
}
