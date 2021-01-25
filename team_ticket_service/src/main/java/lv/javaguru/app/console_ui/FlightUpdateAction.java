package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.utility.TicketFiller;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.response.FlightEditResponse;
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
		Long id = BaseFunc.getMenuNumberFromUserAsLong();

		EditFlightRequest request = new EditFlightRequest(id);
		FlightEditResponse response = flightEditService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			adminMode_initManageTicket(response);
	}

	private void adminMode_initManageTicket (FlightEditResponse response) {
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
			flightEdit_printTicketEditMenu(response.getFlight().getId());

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					editFlightOrigin(response);
				}
				case 2 -> {
					editFlightDestination(response);
				}
				case 3 -> {
					editFlightDepartureDate(response);
				}
				case 4 -> {
					editFlightSeat(response, scanner);
				}
				case 0 -> {
					return;
				}
			}

		}
	}

	private void editFlightOrigin (FlightEditResponse response) {
		TicketFiller filler = new TicketFiller();
		String[] originCountryAndCity = filler.acquireOriginCountryAndCity();

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), originCountryAndCity);
		FlightEditResponse responseEdit = flightEditService.updateOrigin(request);


		if (responseEdit.hasErrors())
			responseEdit.getErrorList().forEach(System.out::println);
		else
			System.out.println(responseEdit.getMessage());
	}

	private void editFlightDestination (FlightEditResponse response) {
		TicketFiller filler = new TicketFiller();
		String[] destinationCountryAndCity = filler.acquireDestinationCountryAndCity();

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), destinationCountryAndCity);
		FlightEditResponse responseEdit = flightEditService.updateDestination(request);


		if (responseEdit.hasErrors())
			responseEdit.getErrorList().forEach(System.out::println);
		else
			System.out.println(responseEdit.getMessage());
	}

	private void editFlightDepartureDate (FlightEditResponse response) {
		TicketFiller ticketFiller = new TicketFiller();
		LocalDate date = ticketFiller.acquireDate(response.getFlight().getTicket());

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), date);
		FlightEditResponse responseEdit = flightEditService.updateDate(request);

		if (responseEdit.hasErrors())
			responseEdit.getErrorList().forEach(System.out::println);
		else
			System.out.println(responseEdit.getMessage());
	}


	private void editFlightSeat (FlightEditResponse response, Scanner scanner) {
		System.out.println("Enter seat number:");
		String seat = scanner.nextLine();

		EditFlightValueRequest editRequest = new EditFlightValueRequest(response.getFlight(), seat);
		FlightEditResponse editResponse = flightEditService.updateSeat(editRequest);

		if (editResponse.hasErrors())
			editResponse.getErrorList().forEach(System.out::println);
		else
			System.out.println(editResponse.getMessage());
	}


	private void editUserName (FlightEditResponse response) {
		Scanner scanner = new Scanner(System.in);

		BaseFunc.printHeader("Enter name:");
		String name = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getFlight(), name);
		FlightEditResponse responseEdit = flightEditService.executeUserNameUpdate(request);


		if (responseEdit.hasErrors())
			responseEdit.getErrorList().forEach(System.out::println);
		else
			System.out.println(responseEdit.getMessage());
	}

	private void editUserSurname (FlightEditResponse response) {
		Scanner scanner = new Scanner(System.in);

		BaseFunc.printHeader("Enter surname:");
		String surname = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getFlight(), surname);
		FlightEditResponse responseEdit = flightEditService.executeUserSurnameUpdate(request);


		if (responseEdit.hasErrors())
			responseEdit.getErrorList().forEach(System.out::println);
		else
			System.out.println(responseEdit.getMessage());
	}

	private void executeUserEditMenu (FlightEditResponse response) {
		while (true) {
			System.out.println("[1] Edit name\n" +
					"[2] Edit surname\n" +
					"[0] Back");
			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					editUserName(response);
				}
				case 2 -> {
					editUserSurname(response);
				}
				case 0 -> {
					return;
				}
			}
		}
	}

	private void flightEdit_printTicketEditMenu (long id) {
		BaseFunc.printHeader("EDIT TICKET: " + id);
		System.out.println(
				"[1] Departure city\n" +
						"[2] Destination city\n" +
						"[3] Departure date\n" +
						"[4] Seat\n" +
						"[0] Back");
	}
}
