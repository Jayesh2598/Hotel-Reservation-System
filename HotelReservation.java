package com.capgemini.hotelReservation;

import java.util.*;

public class HotelReservation {

	private List<Hotel> myHotelList = new ArrayList<Hotel>();

	public List<Hotel> getMyHotelList() {
		return myHotelList;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation Program!");
	}

	// Adding hotel to the list of hotels
	public void addHotel(Hotel hotel) {
		myHotelList.add(hotel);
	}
}
