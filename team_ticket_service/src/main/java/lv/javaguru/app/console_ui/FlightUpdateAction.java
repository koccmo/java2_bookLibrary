package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.edit.*;
import lv.javaguru.app.core.response.EditFlightResponse;
import lv.javaguru.app.core.response.edit.*;
import lv.javaguru.app.core.services.FlightEditService;

import java.time.LocalDate;
import java.util.Scanner;

public class FlightUpdateAction implements UIActions {

	private final FlightEditService flightEditService;

	public FlightUpdateAction (FlightEditService flightEditService) {
		this.flightEditService = flightEditService;
	}

	@Override
	public void execute () {
		BaseFunc.printHeader("Enter flight ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		EditFlightRequest request = new EditFlightRequest(id);
		EditFlightResponse response = flightEditService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			c(response);
	}

	private void c (EditFlightResponse response) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("UPDATE: " + response.getFlight());
			System.out.println("[1] Edit flight's ticket\n" +
					"[2] Edit flight's user\n" +
					"[0] Cancel");

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> executeTicketEditMenu(response);
				case 2 -> executeUserEditMenu(response);

				case 0 -> {
					return;
				}
			}
		}

	}


	private void executeTicketEditMenu (EditFlightResponse response) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			flightEdit_printEditMenu(response.getFlight().getId());

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					editReservation_originCity(response, scanner);
				}
				case 2 -> {
					System.out.println("Enter new destination city: ");
					String destination = scanner.nextLine();

					EditFlightRequest request = new EditFlightRequest(response.getId());
					request.getTicket().setToCity(destination);
					EditFlightResponse responseEdit = flightEditService.executeDestination(request);


					if (responseEdit.hasErrors()) {
						responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					else
						System.out.println("Tickets destination city updated");
				}
				case 3 -> {
					System.out.println("Enter new departure date (2014-02-14): ");
					String departureDate = scanner.nextLine();
					LocalDate date = LocalDate.parse(departureDate);

					EditTicketDepartureDateRequest editTicketDepartureDateRequest = new EditTicketDepartureDateRequest(response.getId(), date);
					EditTicketDepartureDateResponse editTicketDepartureDateResponse = flightEditService.execute(editTicketDepartureDateRequest);

					if (editTicketDepartureDateResponse.hasErrors()) {
						editTicketDepartureDateResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					System.out.println("Ticket's departure date updated!");
				}

				case 4 -> {
					System.out.println("Enter new seat date: ");
					String seat = scanner.nextLine();

					EditTicketSeatRequest editTicketSeatRequest = new EditTicketSeatRequest(response.getId(), seat);
					EditTicketSeatResponse editTicketSeatResponse = flightEditService.execute(editTicketSeatRequest);

					if (editTicketSeatResponse.hasErrors()) {
						editTicketSeatResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					System.out.println("seat updated");
				}
				case 0 -> {
					return;
				}
			}

		}
	}


	private void executeUserEditMenu (EditFlightResponse response) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("[1] Edit name\n" +
					"[2] Edit surname\n" +
					"[0] Back");
			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					editUser_name(response, scanner);
				}
				case 2 -> {
					editUser_surname(response, scanner);
				}
				case 0 -> {
					return;
				}
			}

		}
	}

	private void editUser_name (EditFlightResponse response, Scanner scanner) {
		BaseFunc.printHeader("Enter user name:");
		String name = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getFlight(), name);
		EditFlightResponse responseEdit = flightEditService.executeUserNameUpdate(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("User's name updated");
	}

	private void editUser_surname (EditFlightResponse response, Scanner scanner) {
		BaseFunc.printHeader("Enter user surname:");
		String surname = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getFlight(), surname);
		EditFlightResponse responseEdit = flightEditService.executeUserSurnameUpdate(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("User's surname updated");
	}

	private void editReservation_originCity (EditFlightResponse response, Scanner scanner) {
		BaseFunc.printHeader("Enter new departure city: ");
		String originCity = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getFlight(), originCity);
		EditFlightResponse responseEdit = flightEditService.executeOriginCityUpdate(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("Tickets departure city updated");
	}

	public static void flightEdit_printEditMenu (long id) {
		BaseFunc.printHeader("EDIT TICKET: " + id);
		System.out.println(
				"[1] Departure city\n" +
						"[2] Destination city\n" +
						"[3] Departure date\n" +
						"[4] Seat\n" +
						"[0] Back");
	}
}
