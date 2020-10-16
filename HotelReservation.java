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

	public static void main(String[] args) {
		System.out.println("Welcome to Hotel Reservation Program!");
		String startdate;
		String enddate;
		String customerType;
		try {
			System.out.println("Enter start and end dates: (ddMMMyyyy)");
			startdate = SC.nextLine().trim();
			enddate = SC.nextLine().trim();
			System.out.println("Enter the customer type: (Reward or Regular)");
			customerType = SC.nextLine().trim();
			validateEntries(startdate, enddate, customerType);
			HotelReservation hotelReservation = new HotelReservation();
			Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
			Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
			Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
			hotelReservation.addHotel(hotel1);
			hotelReservation.addHotel(hotel2);
			hotelReservation.addHotel(hotel3);
			System.out.println("Cheapest among the best rated hotels:");
			print(hotelReservation.cheapestBestRatedHotel(startdate, enddate, customerType));
			System.out.println("Best rated among the cheapest hotels:");
			print(hotelReservation.bestRatedCheapestHotel(startdate, enddate, customerType));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Adding hotel to the list of hotels
	public void addHotel(Hotel hotel) {
		myHotelList.add(hotel);
	}

	// Making Hotel-Charges map for the date range
	public Map<Hotel, Long> getHotelCharges(String date1, String date2, String customerType) {
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat format = new SimpleDateFormat("ddMMMyyyy");
		try {
			startDate = format.parse(date1);
			endDate = format.parse(date2);
		} catch (ParseException e) {
			System.out.println("Please enter valid dates");
		}
		int weekDays = 0;
		int weekEnds = 0;
		Date date = startDate;
		while ((date.compareTo(endDate) <= 0)) {
			if (getDayNumber(date) == 1 || getDayNumber(date) == 7)
				weekEnds++;
			else
				weekDays++;
			date = addOneDayToDate(date);
		}
		final int totalWeekDays = weekDays;
		final int totalWeekEnds = weekEnds;
		Map<Hotel, Long> hotelCharges = new HashMap<>();
		if (customerType.equalsIgnoreCase("Regular"))
			hotelCharges = myHotelList.stream()
							.collect(Collectors.toMap(hotel -> (Hotel) hotel,
									hotel -> Long.valueOf(hotel.getWeekdayRegularCustomerRate() * totalWeekDays
									+ hotel.getWeekendRegularCustomerRate() * totalWeekEnds)));
		if (customerType.equalsIgnoreCase("Reward")) {
			hotelCharges = myHotelList.stream()
							.collect(Collectors.toMap(hotel -> (Hotel) hotel,
									hotel -> Long.valueOf(hotel.getWeekdayRewardCustomerRate() * totalWeekDays
									+ hotel.getWeekendRewardCustomerRate() * totalWeekEnds)));
		}
		return hotelCharges;
	}

	// Finding cheapest hotel in the given Hotel-Charges map
	public Map<Hotel, Long> cheapestHotels(Map<Hotel, Long> hotelCharges) {
		Long minCharges = Collections.min(hotelCharges.values());
		hotelCharges = hotelCharges.entrySet().stream()
						.filter(e -> e.getValue().equals(minCharges))
						.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return hotelCharges;
	}

	// Finding the best rated hotel in the given Hotel-Charges map
	public Map<Hotel, Long> bestRatedHotels(Map<Hotel, Long> hotelCharges) {
		List<Integer> ratingsList = hotelCharges.entrySet().stream()
									.map(e -> e.getKey().getRating())
									.collect(Collectors.toList());
		int maxRating = Collections.max(ratingsList);
		hotelCharges = hotelCharges.entrySet().stream()
						.filter(e -> e.getKey().getRating() == maxRating)
						.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return hotelCharges;
	}

	// Finding best rated among the cheapest hotels
	public Map<Hotel, Long> bestRatedCheapestHotel(String date1, String date2, String customerType) {
		return bestRatedHotels(cheapestHotels(getHotelCharges(date1, date2, customerType)));
	}

	// Finding cheapest among the best rated hotels
	public Map<Hotel, Long> cheapestBestRatedHotel(String date1, String date2, String customerType) {
		return cheapestHotels(bestRatedHotels(getHotelCharges(date1, date2, customerType)));
	}

	// Validate user entries
	public static void validateEntries(String date1, String date2, String customerType) throws Exception {
		boolean entryCheck = date1.matches("[0-3][0-9][A-Z][a-z]{2}[0-9]{4}")
							&& date2.matches("[0-3][0-9][A-Z][a-z]{2}[0-9]{4}")
							&& (customerType.equalsIgnoreCase("regular") || customerType.equalsIgnoreCase("reward"));
		boolean dateCheck = (date2.compareTo(date1) >= 0);
		if (!(entryCheck && dateCheck))
			throw new Exception("Invalid input.");
	}

	// Finding the day of the week from given date
	public int getDayNumber(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	// Adding a day to given date
	public Date addOneDayToDate(Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	// Printing hotel info
	public static void print(Map<Hotel, Long> hotelsCharges) {
		hotelsCharges.entrySet().stream().forEach(e -> System.out.println(e.getKey().getHotelName() + ", Rating: "
				+ e.getKey().getRating() + " and Total Rates: $" + e.getValue()));
	}
}
