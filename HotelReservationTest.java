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
	
	@Test
	public void given2DatesShouldReturnCheapestHotelForThePeriod() {
		HotelReservation hotelReservation = new HotelReservation();
		Hotel hotel1 = new Hotel("Lakewood",110);
		Hotel hotel2 = new Hotel("Bridgewood",160);
		Hotel hotel3 = new Hotel("Ridgewood",220);
		hotelReservation.addHotel(hotel1);
		hotelReservation.addHotel(hotel2);
		hotelReservation.addHotel(hotel3);
		Hotel cheapestHotel = hotelReservation.cheapestHotel("10Sep2020", "11Sep2020");
		assertEquals(hotel1, cheapestHotel);
	}
}
