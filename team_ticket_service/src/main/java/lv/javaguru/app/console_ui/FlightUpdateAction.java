package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.utility.TicketFiller;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.response.edit.*;
import lv.javaguru.app.core.services.FlightEditService;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.time.LocalDate;
import java.util.Scanner;

@DIComponent
public class FlightUpdateAction implements UIActions {

	@DIDependency
	private FlightEditService flightEditService;


	@Override
	public void execute () {
		BaseFunc.printHeader("Enter flight ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		EditFlightRequest request = new EditFlightRequest(id);
		FlightEditResponse response = flightEditService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			c(response);
	}

	private void c (FlightEditResponse response) {
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


	private void executeTicketEditMenu (FlightEditResponse response) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			flightEdit_printEditMenu(response.getFlight().getId());

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					editFlight_originCity(response);
				}
				case 2 -> {
					editFlight_destinationCity(response);
				}
				case 3 -> {
					editFlight_departureDate(response, scanner);
				}

				case 4 -> {
					editFlight_seat(response, scanner);
				}
				case 0 -> {
					return;
				}
			}

		}
	}

	private void editFlight_originCity (FlightEditResponse response) {
		TicketFiller filler = new TicketFiller();
		String[] cc = filler.acquireOriginCountryAndCity();
		//Scanner scanner = new Scanner(System.in);

		//BaseFunc.printHeader("Enter new departure city: ");
		//String originCity = scanner.nextLine();

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), cc[1]);
		FlightEditResponse responseEdit = flightEditService.updateOriginCity(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("Tickets departure city updated");
	}

	private void editFlight_destinationCity (FlightEditResponse response) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter new destination city: ");
		String destinationCity = scanner.nextLine();

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), destinationCity);
		FlightEditResponse responseEdit = flightEditService.updateDestinationCity(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("Tickets destination city updated");
	}

	private void editFlight_departureDate (FlightEditResponse response, Scanner scanner) {
		System.out.println("Enter new departure date (2014-02-14): ");
		String departureDate = scanner.nextLine();
		LocalDate date = LocalDate.parse(departureDate);

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), date);
		FlightEditResponse responseEdit = flightEditService.updateDate(request);

		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		System.out.println("Ticket's departure date updated!");
	}

	private void editFlight_seat (FlightEditResponse response, Scanner scanner) {
		System.out.println("Enter new seat date: ");
		String seat = scanner.nextLine();

		EditFlightValueRequest editRequest = new EditFlightValueRequest(response.getFlight(), seat);
		FlightEditResponse editResponse = flightEditService.updateSeat(editRequest);

		if (editResponse.hasErrors()) {
			editResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		System.out.println("seat updated");
	}

	private void executeUserEditMenu (FlightEditResponse response) {
		while (true) {
			System.out.println("[1] Edit name\n" +
					"[2] Edit surname\n" +
					"[0] Back");
			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					editUser_name(response);
				}
				case 2 -> {
					editUser_surname(response);
				}
				case 0 -> {
					return;
				}
			}

		}
	}

	private void editUser_name (FlightEditResponse response) {
		Scanner scanner = new Scanner(System.in);

		BaseFunc.printHeader("Enter user name:");
		String name = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getFlight(), name);
		FlightEditResponse responseEdit = flightEditService.executeUserNameUpdate(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("User's name updated");
	}

	private void editUser_surname (FlightEditResponse response) {
		Scanner scanner = new Scanner(System.in);

		BaseFunc.printHeader("Enter user surname:");
		String surname = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getFlight(), surname);
		FlightEditResponse responseEdit = flightEditService.executeUserSurnameUpdate(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("User's surname updated");
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
