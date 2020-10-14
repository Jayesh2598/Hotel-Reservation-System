package com.capgemini.hotelReservation;

public class Hotel {

	private String hotelName;
	private int regularCustomerRate;

	public Hotel(String hotelName, int regularCustomerRate) {
		this.hotelName = hotelName;
		this.regularCustomerRate = regularCustomerRate;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getRegularCustomerRate() {
		return regularCustomerRate;
	}
}
