package com.capgemini.hotelReservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class HotelReservationTest {

	@Test
	public void givenHotelDetailsShouldAddHotelToMyHotelList() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel(new Hotel("Lakewood", 110, 90,3));
		int size = hotelReservation.getMyHotelList().size();
		assertEquals(1, size);
	}

	@Test
	public void given2DatesShouldReturnCheapestHotelsForTheDateRange() {
		HotelReservation hotelReservation = new HotelReservation();
		Hotel hotel1 = new Hotel("Lakewood", 110, 90,3);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50,4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150,5);
		hotelReservation.addHotel(hotel1);
		hotelReservation.addHotel(hotel2);
		hotelReservation.addHotel(hotel3);
		List<Hotel> cheapestHotelsList = hotelReservation.cheapestHotel("11Sep2020", "12Sep2020");
		assertTrue(cheapestHotelsList.contains(hotel1));
		assertTrue(cheapestHotelsList.contains(hotel2));
	}
}
