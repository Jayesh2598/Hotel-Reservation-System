package com.capgemini.hotelReservation;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HotelReservation {

	static Scanner SC = new Scanner(System.in);
	
	private List<Hotel> myHotelList = new ArrayList<Hotel>();

	public List<Hotel> getMyHotelList() {
		return myHotelList;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println("Welcome to Hotel Reservation Program!");
		System.out.println("Enter start and end dates: (ddMMMyyyy)");
		String startdate = SC.next();
		String enddate = SC.next();
		HotelReservation hotelReservation = new HotelReservation();
		Hotel hotel1 = new Hotel("Lakewood",110);
		Hotel hotel2 = new Hotel("Bridgewood",160);
		Hotel hotel3 = new Hotel("Ridgewood",220);
		hotelReservation.addHotel(hotel1);
		hotelReservation.addHotel(hotel2);
		hotelReservation.addHotel(hotel3);
		hotelReservation.cheapestHotel(startdate, enddate);
	}

	// Adding hotel to the list of hotels
	public void addHotel(Hotel hotel) {
		myHotelList.add(hotel);
	}
	
	//Finding cheapest hotel for given date range
	public Hotel cheapestHotel(String date1, String date2) {
		Date startDate=null;
		Date endDate=null;
		SimpleDateFormat format = new SimpleDateFormat("ddMMMyyyy");
		try {
			startDate = format.parse(date1);
			endDate = format.parse(date2);
		} catch (ParseException e) {
			System.out.println("Please enter valid dates");
		}
		long days = Math.abs(endDate.getTime() - startDate.getTime())/(1000*60*60*24);	
		List<Long> chargesList = new ArrayList<>();
		for(Hotel hotel: myHotelList) {
			long charges = hotel.getRegularCustomerRate()*(days+1);
			chargesList.add(charges);
		}
		int hotelIndex = chargesList.indexOf(Collections.min(chargesList));
		System.out.println(myHotelList.get(hotelIndex).getHotelName()+", Total Rates: $"+chargesList.get(hotelIndex));
		return myHotelList.get(hotelIndex);
	}
}
