package com.capgemini.hotelReservation;

import java.util.*;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HotelReservation {

	static Scanner SC = new Scanner(System.in);

	Calendar calendar = Calendar.getInstance();

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
		Hotel hotel1 = new Hotel("Lakewood", 110, 90,3);
		Hotel hotel2 = new Hotel("Bridgewood", 150, 50,4);
		Hotel hotel3 = new Hotel("Ridgewood", 220, 150,5);
		hotelReservation.addHotel(hotel1);
		hotelReservation.addHotel(hotel2);
		hotelReservation.addHotel(hotel3);
		hotelReservation.cheapestBestRatedHotel(startdate, enddate);
	}

	// Adding hotel to the list of hotels
	public void addHotel(Hotel hotel) {
		myHotelList.add(hotel);
	}

	// Finding cheapest hotel for given date range
	public Map<Hotel,Integer> cheapestBestRatedHotel(String date1, String date2) {
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat format = new SimpleDateFormat("ddMMMyyyy");
		try {
			startDate = format.parse(date1);
			endDate = format.parse(date2);
		} catch (ParseException e) {
			System.out.println("Please enter valid dates");
		}
		
		//Calculating weekends and weekdays in the date range
		int weekDays = 0;
		int weekEnds = 0;
		Date date = startDate;
		while ((date.compareTo(endDate) <= 0)) {
			if (getDayNumber(date) == 1 || getDayNumber(date) == 7)
				weekEnds++;
			else
				weekDays++;
			date = addDayToDate(date);
		}
		
		//Mapping hotel with it's charges for the date range
		Map<Hotel,Long> hotelCharges = new HashMap<>();
		for (Hotel hotel : myHotelList) {
			long charges = hotel.getWeekdayRegularCustomerRate() * weekDays
							+ hotel.getWeekendRegularCustomerRate() * weekEnds;
			hotelCharges.put(hotel, charges);
		}
		
		//Picking cheapest hotel(s) as Map<Hotel,Rating>
		Long minCharges = Collections.min(hotelCharges.values());
		Map<Hotel,Integer> cheapestHotelsAndRatings = hotelCharges.entrySet().stream()
													.filter(e -> e.getValue().equals(minCharges))
													.collect(Collectors.toMap(p -> p.getKey(), p -> p.getKey().getRating()));
		
		//Modifying cheapestHotelsAndRatings to contain only hotel(s) with maximum rating
		int maxRating = Collections.max(cheapestHotelsAndRatings.values());
		cheapestHotelsAndRatings = cheapestHotelsAndRatings.entrySet().stream()
										.filter(e -> e.getValue().equals(maxRating))
										.collect(Collectors.toMap(p-> p.getKey(), p -> p.getValue()));
		
		cheapestHotelsAndRatings.entrySet().stream()
		.forEach(e-> System.out.println(e.getKey().getHotelName()+", Rating: "+e.getKey().getRating()+" and Total Rates: $"+minCharges));
		
		return cheapestHotelsAndRatings;
	}

	// Finding the day of the week from given date
	public int getDayNumber(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	// Adding a day to given date
	public Date addDayToDate(Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
}
