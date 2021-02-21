package lv.javaguru.app.console_ui.utility;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Ticket;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class TicketFiller {

	private Ticket ticket;

	public TicketFiller () {
	}

	public TicketFiller (Ticket ticket) {
		this.ticket = ticket;
	}

	public boolean fill () {
		Scanner scanner = new Scanner(System.in);

		String[] origin = acquireOriginCountryAndCity();
		if (origin != null) {
			ticket.setOriginCountry(origin[0]);
			ticket.setOriginCity(origin[1]);
		}
		else {
			return false;
		}

		String[] destination = acquireDestinationCountryAndCity();
		if (destination != null) {
			ticket.setDestinationCountry(destination[0]);
			ticket.setDestinationCity(destination[1]);
		}
		else {
			return false;
		}

	//	Date date = acquireDate(ticket);
	//	if (date != null)
	//		ticket.setDepartureDate(date);
	//	else
	//		return false;


		System.out.println("Enter seat: ");
		String seat = scanner.nextLine();
		seat = seat.trim();
		ticket.setSeat(seat);
		return true;
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

	private String getUserInput () {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		input = input.trim().toLowerCase();

		return input;
	}

	public String[] acquireOriginCountryAndCity () {
		return acquireCountryAndCity(countryOrigin, cityOrigin);
	}

	public String[] acquireDestinationCountryAndCity () {
		return acquireCountryAndCity(destinationCountry, destinationCity);
	}

	private String[] acquireCountryAndCity (Map<Integer, String> countryMap, Map<String, List<String>> citiesMap) {
		String country;
		String city;

		while (true) {
			country = acquireCountry(countryMap);

			if (!country.equals("CANCELED")) {
				List<String> cities = citiesMap.get(country);
				city = acquireCity(cities);

				if (city != null && !city.equals("CANCELED")) {
					return new String[]{country, city};
				}
			}
			else break;
		}
		return null;
	}


	private String acquireCountry (Map<Integer, String> countryMap) {
		String input;

		String country;

		while (true) {
			BaseFunc.printHeader("SELECT COUNTRY:");
			printMap(countryMap);

			if (isInputValid(input = getUserInput())) {
				if ("x".equals(input)) {
					country = "CANCELED";
					break;
				}

				int key = Integer.parseInt(input);

				if (countryMap.get(key) != null) {
					country = countryMap.get(key);
					break;
				}

			}
		}
		return country;
	}

	private String acquireCity (List<String> cities) {
		String input;

		String city = null;

		while (true) {
			BaseFunc.printHeader("SELECT CITY:");
			printList(cities);

			if (isInputValid(input = getUserInput())) {
				if ("x".equals(input)) {
					city = "CANCELED";
					break;
				}
				if (input.equalsIgnoreCase("0")) {
					break;
				}
				int i = Integer.parseInt(input) - 1;

				if (i >= 0 && i < cities.size()) {
					city = cities.get(i);
					break;
				}

			}
		}
		return city;
	}

	public Date acquireDate (Ticket ticket) {
		String input;
		LocalDate date;

		while (true) {
			BaseFunc.printHeader("SELECT DATE:");
			List<LocalDate> dates = new ArrayList<>();

			if (ticket.getDestinationCity().equals("Paphos"))
				dates.addAll(departEvery(new int[]{2, 6}));
			else if (ticket.getDestinationCity().equals("Malta"))
				dates.addAll(departEvery(new int[]{4, 7}));

			printDates(dates);

			if (isInputValid(input = getUserInput())) {
				if (input.equalsIgnoreCase("X")) {
					return null;
				}

				int index = Integer.parseInt(input);

				if (index != 0 && dates.get(index - 1) != null) {
					date = dates.get(index - 1);
					break;
				}
			}
		}
		ZoneId defaultZoneId = ZoneId.systemDefault();

		return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
	}

	private boolean isInputValid (String input) {
		return !input.isEmpty() && input.matches("^\\d+$|^[xX]$");
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
}
