package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.utility.TicketFiller;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.services.FlightEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Scanner;

@Component
public class FlightUpdateAction extends Action implements UIActions {

	@Autowired
	private FlightEditService flightEditService;


	@Override
	public void execute () {
		BaseFunc.printHeader("Enter flight ID:");
		Long id = BaseFunc.getMenuNumberFromUserAsLong();

		EditFlightRequest request = new EditFlightRequest(id, getLoggedInUser());
		FlightEditResponse response = flightEditService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			adminMode_initManageTicket(response);
	}

	private void adminMode_initManageTicket (FlightEditResponse response) {

		int menuNumber;
		while (true) {
			System.out.println("UPDATE: " + response.getFlight());
			BaseFunc.printLineSeparator();
			//if (getLoggedInUser().getPersonType() == PersonType.ADMIN) {
			if (true) {
				System.out.println("[1] Edit flight's ticket\n" +
						"[2] Edit flight's user\n\n" +
						"[0] Cancel");

				menuNumber = BaseFunc.getMenuNumberFromUser();

				switch (menuNumber) {
					case 1 -> executeTicketEditMenu(response);
					case 2 -> executeUserEditMenu(response);

					case 0 -> {
						return;
					}
				}
			}
			else {
				System.out.println("[1] Edit flight's ticket\n\n" +
						"[0] Cancel");

				switch (BaseFunc.getMenuNumberFromUser()) {
					case 1 -> executeTicketEditMenu(response);
					case 0 -> {
						return;
					}
				}
			}
		}

	}


	private void executeTicketEditMenu (FlightEditResponse response) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			flightEdit_printTicketEditMenu(response.getFlight().getTicket().getId());

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

		Date date = ticketFiller.acquireDate(response.getFlight().getTicket());

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

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), name);
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

		EditFlightValueRequest request = new EditFlightValueRequest(response.getFlight(), surname);
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

	private void flightEdit_printTicketEditMenu (Long id) {
		BaseFunc.printHeader("EDIT TICKET: ", String.valueOf(id));
		System.out.println(
				"[1] Origin\n" +
						"[2] Destination\n" +
						"[3] Departure date\n" +
						"[4] Seat\n" +
						"[0] Back");
	}
}
