package com.capgemini.hotelReservation;

public class Hotel {

	private String hotelName;
	private int weekdayRegularCustomerRate;
	private int weekendRegularCustomerRate;
	private int weekdayRewardCustomerRate;
	private int weekendRewardCustomerRate;
	private int rating;

	public Hotel(String hotelName, int weekdayRegularCustomerRate, int weekendRegularCustomerRate,
			int weekdayRewardCustomerRate, int weekendRewardCustomerRate, int rating) {
		this.hotelName = hotelName;
		this.weekdayRegularCustomerRate = weekdayRegularCustomerRate;
		this.weekendRegularCustomerRate = weekendRegularCustomerRate;
		this.weekdayRewardCustomerRate = weekdayRewardCustomerRate;
		this.weekendRewardCustomerRate = weekendRewardCustomerRate;
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

	public int getWeekdayRewardCustomerRate() {
		return weekdayRewardCustomerRate;
	}

	public int getWeekendRewardCustomerRate() {
		return weekendRewardCustomerRate;
	}
}
