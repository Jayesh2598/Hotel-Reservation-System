package com.capgemini.hotelReservation;

public class Hotel {

	private String hotelName;
	private int weekdayRegularCustomerRate;
	private int weekendRegularCustomerRate;
	private int rating;

	public Hotel(String hotelName, int weekdayRegularCustomerRate, int weekendRegularCustomerRate, int rating) {
		this.hotelName = hotelName;
		this.weekdayRegularCustomerRate = weekdayRegularCustomerRate;
		this.weekendRegularCustomerRate = weekendRegularCustomerRate;
		this.rating = rating;
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
	
	public int getRating() {
		return rating;
	}
}
