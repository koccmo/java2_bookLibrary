package lv.javaguru.app.console_ui.utility;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Ticket;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketFiller {

	private  Ticket ticket;
	public TicketFiller () {
	}
	public TicketFiller (Ticket ticket) {
		this.ticket = ticket;
	}

	public boolean fill () {
		Scanner scanner = new Scanner(System.in);

		String[] origin = acquireOriginCountryAndCity();
		if (origin != null) {
			ticket.setFromCountry(origin[0]);
			ticket.setFromCity(origin[1]);
		}
		else {
			return false;
		}

		acquireDestinationCountryAndCity(ticket);
		if (ticket.isCanceled())
			return false;


		acquireDepartureDate(ticket);
		if (ticket.isCanceled())
			return false;


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
		String country;
		String city;

		while (true) {
			country = acquireOriginCountry();

			if (!country.equals("CANCELED")) {
				city = acquireOriginCity(country);

				if (city != null && !city.equals("CANCELED")) {
					return new String[]{country, city};
				}
			}
			else break;
		}
		return null;
	}

	public void acquireDestinationCountryAndCity (Ticket ticket) {
		ticket.setCanceled(false);

		boolean editing = true;

		while (editing && !ticket.isCanceled()) {
			String country = acquireDestinationCountry(ticket);
			String city = acquireDestinationCity(ticket, country);

			if (country != null && city != null) {
				ticket.setToCountry(country);
				ticket.setToCity(city);
				editing = false;
			}
		}
	}


	private String acquireOriginCountry () {
		String input;

		String country;

		while (true) {
			BaseFunc.printHeader("SELECT ORIGIN COUNTRY:");
			printMap(countryOrigin);

			if (isInputValid(input = getUserInput())) {
				if ("x".equals(input)) {
					country = "CANCELED";
					//ticket.setCanceled(true);
					break;
				}

				int key = Integer.parseInt(input);

				if (countryOrigin.get(key) != null) {
					country = countryOrigin.get(key);
					break;
				}

			}
		}
		return country;
	}

	private String acquireOriginCity (String country) {
		List<String> cities = cityOrigin.get(country);

		String input;

		String city = null;

		while (true) {
			BaseFunc.printHeader("SELECT ORIGIN CITY:");
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


	private String acquireDestinationCountry (Ticket ticket) {
		String input;

		String country = null;

		while (!ticket.isCanceled()) {
			BaseFunc.printHeader("SELECT DESTINATION COUNTRY:");
			printMap(destinationCountry);

			if (isInputValid(input = getUserInput())) {
				if ("x".equals(input)) {
					ticket.setCanceled(true);
					break;
				}

				int key = Integer.parseInt(input);

				if (destinationCountry.get(key) != null) {
					country = destinationCountry.get(key);
					break;
				}
			}
		}
		return country;
	}

	private String acquireDestinationCity (Ticket ticket, String country) {
		if (country == null)
			return null;
		List<String> cities = destinationCity.get(country);

		String input;
		String city = null;

		while (!ticket.isCanceled()) {
			BaseFunc.printHeader("SELECT DESTINATION CITY:");
			printList(cities);

			if (isInputValid(input = getUserInput())) {
				if (input.equals("x")) {
					ticket.setCanceled(true);
					break;
				}
				if (input.equals("0")) {
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

	private void acquireDepartureDate (Ticket ticket) {
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
