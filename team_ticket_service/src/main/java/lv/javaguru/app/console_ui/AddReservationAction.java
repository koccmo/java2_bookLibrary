package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.services.AddTicketService;
import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddTicketRequest;
import lv.javaguru.app.core.response.AddTicketResponse;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddReservationAction extends Action implements UIActions {

	private final AddTicketService addTicketService;

	public AddReservationAction (AddTicketService addTicketService) {
		this.addTicketService = addTicketService;
	}


	@Override
	public void execute () {
		Ticket ticket = fillTicket();
		if (ticket == null)
			return;

		Person currUser = getLoggedInUser();

		AddTicketRequest request = new AddTicketRequest(currUser, ticket);
		AddTicketResponse response = addTicketService.execute(request);

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


	private Ticket fillTicket () {
		Scanner scanner = new Scanner(System.in);

		try {
			String originCountry = selectOriginCountry();
			String originCity = selectOriginCity(originCountry);

			String destinationCountry = selectDestinationCountry();
			String destinationCity = selectDestinationCity(destinationCountry);


			System.out.println("Enter departure date (format: dd-MM-yyyy):");
			String departureDate = scanner.nextLine();
			departureDate = departureDate.trim();

			System.out.println("Enter return date (format: dd-MM-yyyy):");
			String returnDate = scanner.nextLine();
			returnDate = returnDate.trim();

			System.out.println("Enter seat: ");
			String seat = scanner.nextLine();
			seat = seat.trim();

			return new Ticket(originCity, destinationCity, departureDate, returnDate, seat);

		} catch (Exception e) {
			return null;
		}
	}


	private String selectOriginCountry () throws Exception {
		Map<Integer, String> originCountry = new HashMap<>() {{
			put(0, "Back");
			put(1, "Latvia");
		}};

		return getSelection("Select origin country:", originCountry);
	}


	private String selectOriginCity (String originCountry) throws Exception {
		Map<String, List<String>> countryCities = new HashMap<>() {{
			put("Latvia", new ArrayList<>() {{
				add("Riga");
			}});
		}};

		List<String> cities = countryCities.get(originCountry);
		Map<Integer, String> citiesToSelect = listToMapConverter(cities);

		return getSelection("Select origin city:", citiesToSelect);
	}


	private String selectDestinationCountry () throws Exception {
		Map<Integer, String> destinationCountry = new HashMap<>() {{
			put(0, "Back");
			put(1, "Cyprus");
		}};

		String selection = getSelection("Select destination country:", destinationCountry);

		if (selection.equals("Back"))
			throw new Exception("Exiting...");

		return selection;

	}


	private String selectDestinationCity (String destinationCountry) throws Exception {
		Map<String, List<String>> destination = new HashMap<>() {{
			put("Cyprus", new ArrayList<>() {{
				add("Paphos");
			}});
		}};

		List<String> destinationCities = destination.get(destinationCountry);
		Map<Integer, String> citiesToSelect = listToMapConverter(destinationCities);

		return getSelection("Select destination city:", citiesToSelect);
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


	private void printMap (Map<Integer, String> map) {
		if (map == null)
			return;

		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("[" + entry.getKey() + "] " + entry.getValue());
		}
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
}

