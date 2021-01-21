package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.services.AddReservationService;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddTicketRequest;
import lv.javaguru.app.core.response.AddTicketResponse;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddReservationAction extends Action implements UIActions {

	private final AddReservationService addReservationService;

	public AddReservationAction (AddReservationService addReservationService) {
		this.addReservationService = addReservationService;
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
		Reservation reservation = new Reservation(currUser, ticket);

		AddTicketRequest request = new AddTicketRequest(reservation);
		AddTicketResponse response = addReservationService.execute(request);

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

		acquireOriginCountryAndCity(ticket);
		if (ticket.isCanceled())
			return;
		acquireDestinationCountryAndCity(ticket);

		if (ticket.isCanceled())
			return;

		System.out.println("Enter departure date (format: dd-MM-yyyy):");
		String departureDate = scanner.nextLine();
		departureDate = departureDate.trim();
		ticket.setDepartDate(departureDate);

		System.out.println("Enter return date (format: dd-MM-yyyy):");
		String returnDate = scanner.nextLine();
		returnDate = returnDate.trim();
		ticket.setReturnDate(returnDate);

		System.out.println("Enter seat: ");
		String seat = scanner.nextLine();
		seat = seat.trim();
		ticket.setSeat(seat);

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

	private static void acquireDestinationCountryAndCity (Ticket ticket) {
		String input;

		ticket.setFinished(false);
		ticket.setCanceled(false);

		while (!ticket.isCanceled() && !ticket.isFinished()) {
			System.out.println("Select destination country:");
			BaseFunc.printLineSeparator();

			printMap(destinationCountry);

			input = getUserInput();

			if (isInputValid(input)) {
				if (input.equalsIgnoreCase("0"))
					break;

				else if (input.equalsIgnoreCase("X"))
					ticket.setCanceled(true);

				else if (destinationCountry.get(Integer.valueOf(input)) != null) {
					String country = destinationCountry.get(Integer.valueOf(input));
					ticket.setDestinationCountry(country);

					acquireDestinationCity(ticket);
				}
			}
		}
		BaseFunc.printLineSeparator();
	}

	private static void acquireDestinationCity (Ticket ticket) {
		List<String> cities = destinationCity.get(ticket.getDestinationCountry());

		String newInput;

		while (true) {
			printList(cities);

			newInput = getUserInput();

			if (isInputValid(newInput)) {
				if (newInput.equalsIgnoreCase("0")) {
					return;
				}
				else if (newInput.equalsIgnoreCase("X")) {
					ticket.setCanceled(true);
					return;
				}
				else {
					try {
						int i = Integer.parseInt(newInput) - 1;
						ticket.setDestinationCity(cities.get(i));
						ticket.setFinished(true);
						return;
					} catch (Exception ignore) {
					}
				}
			}
		}
	}

	private static void acquireOriginCountryAndCity (Ticket ticket) {
		String input;

		ticket.setFinished(false);
		ticket.setCanceled(false);

		while (!ticket.isCanceled() && !ticket.isFinished()) {
			printMap(countryOrigin);

			input = getUserInput();

			if (isInputValid(input)) {
				if (input.equalsIgnoreCase("0"))
					break;

				else if (input.equalsIgnoreCase("X"))
					ticket.setCanceled(true);

				else if (countryOrigin.get(Integer.valueOf(input)) != null) {
					String country = countryOrigin.get(Integer.valueOf(input));
					ticket.setOriginCountry(country);

					acquireOriginCity(ticket);
				}
			}
		}
		BaseFunc.printLineSeparator();
	}

	private static void acquireOriginCity (Ticket ticket) {
		List<String> cities = cityOrigin.get(ticket.getOriginCountry());

		String newInput;

		while (true) {
			printList(cities);

			newInput = getUserInput();

			if (isInputValid(newInput)) {
				if (newInput.equalsIgnoreCase("0")) {
					return;
				}
				else if (newInput.equalsIgnoreCase("X")) {
					ticket.setCanceled(true);
					return;
				}
				else {
					try {
						int i = Integer.parseInt(newInput) - 1;
						ticket.setOriginCity(cities.get(i));
						ticket.setFinished(true);
						return;
					} catch (Exception ignore) {
					}
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

	private static void printMap (Map<Integer, String> map) {
		if (map.size() == 0)
			return;

		for (int i = 1; i <= map.size(); i++)
			System.out.println("[" + i + "] " + map.get(i));
		System.out.println("\n[X] Cancel");
	}
}

