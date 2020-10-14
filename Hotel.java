package com.capgemini.hotelReservation;

public class Hotel {

	private String hotelName;
	private int weekdayRegularCustomerRate;
	private int weekendRegularCustomerRate;

	public Hotel(String hotelName, int weekdayRegularCustomerRate, int weekendRegularCustomerRate) {
		this.hotelName = hotelName;
		this.weekdayRegularCustomerRate = weekdayRegularCustomerRate;
		this.weekendRegularCustomerRate = weekendRegularCustomerRate;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getWeekdayRegularCustomerRate() {
		return weekdayRegularCustomerRate;
	}

	public int getWeekendRegularCustomerRate() {
		return weekendRegularCustomerRate;
	}
}
