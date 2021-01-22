package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.services.FlightAddService;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.response.AddFlightResponse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightAddAction extends Action implements UIActions {

	private final FlightAddService flightAddService;

	public FlightAddAction (FlightAddService flightAddService) {
		this.flightAddService = flightAddService;
	}

	private static final Map<String, List<String>> cityOrigin = new HashMap<>() {{
		put("Latvia", new ArrayList<>() {{
			add("Riga");
		}});
		put("Lithuania", new ArrayList<>() {{
			add("Kaunas");
			add("Palanga");
			add("Vilnius");
		}});
	}};

	private static final Map<Integer, String> countryOrigin = new HashMap<>() {{
		put(1, "Latvia");
		put(2, "Lithuania");
	}};

	private static final Map<String, List<String>> destinationCity = new HashMap<>() {{
		put("Malta", new ArrayList<>() {{
			add("Malta");
		}});
		put("Cyprus", new ArrayList<>() {{
			add("Paphos");
		}});
	}};

	private static final Map<Integer, String> destinationCountry = new HashMap<>() {{
		put(1, "Cyprus");
		put(2, "Malta");
	}};


	@Override
	public void execute () {
		Ticket ticket = new Ticket();
		fillTicket(ticket);

		if (ticket.isCanceled())
			return;

		if (ticket.isFinished())
			System.out.println(ticket.toString());

		User currUser = getLoggedInUser();
		Flight flight = new Flight(currUser, ticket);

		AddFlightRequest request = new AddFlightRequest(flight);
		AddFlightResponse response = flightAddService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			System.out.println("Your reservation was added to list.");
	}

	private static boolean validateCity (String city) {
		Pattern pattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(city);

		// return matcher.matches();
		return city.matches("(?i)[a-z]+");
	}

	private static String capitalize (String city) {
		city = city.toLowerCase().trim();
		city = city.substring(0, 1).toUpperCase() + city.substring(1);

		return city;
	}


	private void fillTicket (Ticket ticket) {
		Scanner scanner = new Scanner(System.in);

	/*	acquireOriginCountry(ticket);
		if (ticket.isCanceled())
			return;
		acquireOriginCity(ticket);*/
		acquireOriginCountryAndCity(ticket);
		if (ticket.isCanceled())
			return;

		acquireDestinationCountry(ticket);
		if (ticket.isCanceled())
			return;
		acquireDestinationCity(ticket);
		if (ticket.isCanceled())
			return;

		acquireDepartureDate(ticket);
		if (ticket.isCanceled())
			return;


		System.out.println("Enter seat: ");
		String seat = scanner.nextLine();
		seat = seat.trim();
		ticket.setSeat(seat);

	}

	private static List<LocalDate> departEvery (int[] dayOfWeek) {
		LocalDate now = LocalDate.now();
		LocalDate next;
		int k = 0;

		List<LocalDate> dates = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			int day = dayOfWeek[k++];
			if (k == dayOfWeek.length)
				k = 0;
			next = now.with(TemporalAdjusters.next(DayOfWeek.of(day)));
			now = next;
			dates.add(next);
		}
		return dates;
	}


	private Map<Integer, String> listToMapConverter (List<String> list) {
		if (list == null)
			return null;

		Map<Integer, String> map = new HashMap<>();
		map.put(0, "Back");

		int i = 1;

		for (String str : list)
			map.put(i++, str);

		return map;
	}

	private String getSelection (String message, Map<Integer, String> map) throws Exception {
		while (true) {
			System.out.println(message);
			printMap(map);
			int menuNumber = BaseFunc.getMenuNumberFromUser();

			if (menuNumber == 0)
				throw new Exception("Exiting...");

			if (map.get(menuNumber) != null)
				return map.get(menuNumber);
		}
	}


	private static String getUserInput () {
		Scanner scanner = new Scanner(System.in);

		return scanner.nextLine();
	}

	private static void acquireOriginCountryAndCity (Ticket ticket) {
		while (!ticket.isOriginSelected()) {
			acquireOriginCountry(ticket);
			acquireOriginCity(ticket);
		}
	}


	private static void acquireOriginCountry (Ticket ticket) {
		String input;
		ticket.setFromCountry(null);

		while (!ticket.isCanceled()) {
			BaseFunc.printHeader("SELECT ORIGIN COUNTRY:");
			printMap(countryOrigin);

			input = getUserInput();

			if (isInputValid(input)) {
				if (input.equalsIgnoreCase("X"))
					ticket.setCanceled(true);

				int index = Integer.parseInt(input);

				if (index != 0 && countryOrigin.get(index) != null) {
					String country = countryOrigin.get(index);
					ticket.setFromCountry(country);

					return;
				}
			}
		}
	}

	private static void acquireOriginCity (Ticket ticket) {
		List<String> cities = cityOrigin.get(ticket.getFromCountry());

		String newInput;

		ticket.setFromCity(null);

		while (!ticket.isCanceled()) {
			BaseFunc.printHeader("SELECT ORIGIN CITY:");
			printList(cities);

			newInput = getUserInput();

			if (isInputValid(newInput)) {
				if (newInput.equalsIgnoreCase("0")) {
					break;
				}
				else if (newInput.equalsIgnoreCase("X")) {
					ticket.setCanceled(true);
					return;
				}
				else {
					try {
						int i = Integer.parseInt(newInput) - 1;
						ticket.setFromCity(cities.get(i));

						return;
					}
					catch (Exception ignore) {
					}
				}
			}
		}
	}

	private static void acquireDestinationCountry (Ticket ticket) {
		String input;

		while (!ticket.isCanceled()) {
			BaseFunc.printHeader("SELECT DESTINATION COUNTRY:");

			printMap(destinationCountry);

			input = getUserInput();

			if (isInputValid(input)) {
				if (input.equalsIgnoreCase("X"))
					ticket.setCanceled(true);

				int index = Integer.parseInt(input);

				if (index != 0 && destinationCountry.get(index) != null) {
					String country = destinationCountry.get(index);
					ticket.setToCountry(country);

					return;
				}
			}
		}
	}

	private static void acquireDestinationCity (Ticket ticket) {
		List<String> cities = destinationCity.get(ticket.getToCountry());

		String newInput;

		while (!ticket.isCanceled()) {
			BaseFunc.printHeader("SELECT DESTINATION CITY:");
			printList(cities);

			newInput = getUserInput();

			if (isInputValid(newInput)) {
				if (newInput.equalsIgnoreCase("0")) {
					break;
				}
				else if (newInput.equalsIgnoreCase("X")) {
					ticket.setCanceled(true);
					return;
				}
				else {
					try {
						int i = Integer.parseInt(newInput) - 1;
						ticket.setToCity(cities.get(i));

						return;
					}
					catch (Exception ignore) {
					}
				}
			}
		}
	}

	private static void acquireDepartureDate (Ticket ticket) {
		String input;

		while (!ticket.isCanceled()) {
			BaseFunc.printHeader("SELECT DATE:");
			List<LocalDate> dates = new ArrayList<>();

			if (ticket.getToCity().equals("Paphos"))
				dates.addAll(departEvery(new int[]{2, 6}));
			else if (ticket.getToCity().equals("Malta"))
				dates.addAll(departEvery(new int[]{4, 7}));

			printDates(dates);

			input = getUserInput();

			if (isInputValid(input)) {
				if (input.equalsIgnoreCase("X"))
					ticket.setCanceled(true);

				int index = Integer.parseInt(input);

				if (index != 0 && dates.get(index - 1) != null) {
					LocalDate date = dates.get(index - 1);
					ticket.setDate(date);

					return;
				}
			}
		}
	}


	private static boolean isInputValid (String input) {
		return !input.isEmpty() && input.matches("(?i)[<x]{1}|^\\d{1,2}$");
	}

	private static void printList (List<String> list) {
		for (int i = 1; i <= list.size(); i++)
			System.out.println("[" + i + "] " + list.get(i - 1));
		System.out.println("[0] Back");
		System.out.println("\n[X] Cancel");
	}

	private static void printDates (List<LocalDate> list) {
		for (int i = 1; i <= list.size(); i++)
			System.out.println("[" + i + "] " + list.get(i - 1));
		System.out.println("\n[X] Cancel");
	}

	private static void printMap (Map<Integer, String> map) {
		if (map.size() == 0)
			return;

		for (int i = 1; i <= map.size(); i++)
			System.out.println("[" + i + "] " + map.get(i));
		System.out.println("\n[X] Cancel");
	}
}

