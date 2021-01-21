package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.edit.*;
import lv.javaguru.app.core.response.EditFlightResponse;
import lv.javaguru.app.core.response.edit.*;
import lv.javaguru.app.core.services.EditFlightService;

import java.util.Scanner;

public class EditFlightAction implements UIActions {

	private final EditFlightService editFlightService;

	public EditFlightAction (EditFlightService editFlightService) {
		this.editFlightService = editFlightService;
	}

	@Override
	public void execute () {
		BaseFunc.printHeader("Enter ticket ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		EditFlightRequest request = new EditFlightRequest(id);
		EditFlightResponse response = editFlightService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			executeReservationEditMenu(response);
	}


	private void executeReservationEditMenu (EditFlightResponse response) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			reservationEdit_printEditMenu(response.getReservation().getId());

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					editReservation_originCity(response, scanner);
				}
				case 2 -> {
					System.out.println("Enter new destination city: ");
					String destination = scanner.nextLine();

					EditFlightRequest request = new EditFlightRequest(response.getId());
					request.getTicket().setDestinationCity(destination);
					EditFlightResponse responseEdit = editFlightService.executeDestination(request);


					if (responseEdit.hasErrors()) {
						responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					else
						System.out.println("Tickets destination city updated");
				}
				case 3 -> {
					System.out.println("Enter new departure date: ");
					String departureDate = scanner.nextLine();

					EditTicketDepartureDateRequest editTicketDepartureDateRequest = new EditTicketDepartureDateRequest(response.getId(), departureDate);
					EditTicketDepartureDateResponse editTicketDepartureDateResponse = editFlightService.execute(editTicketDepartureDateRequest);

					if (editTicketDepartureDateResponse.hasErrors()) {
						editTicketDepartureDateResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					System.out.println("Tickets departure date updated");
				}
				case 4 -> {
					System.out.println("Enter new return date: ");
					String returnDate = scanner.nextLine();

					EditTicketArrivalDateRequest editTicketArrivalDateRequest = new EditTicketArrivalDateRequest(response.getId(), returnDate);
					EditTicketReturnDateResponse editTicketReturnDateResponse = editFlightService.execute(editTicketArrivalDateRequest);

					if (editTicketReturnDateResponse.hasErrors()) {
						editTicketReturnDateResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					System.out.println("Tickets arrival date updated");
				}
				case 5 -> {
					System.out.println("Enter new seat date: ");
					String seat = scanner.nextLine();

					EditTicketSeatRequest editTicketSeatRequest = new EditTicketSeatRequest(response.getId(), seat);
					EditTicketSeatResponse editTicketSeatResponse = editFlightService.execute(editTicketSeatRequest);

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

	private void editReservation_originCity (EditFlightResponse response, Scanner scanner) {
		BaseFunc.printHeader("Enter new departure city: ");
		String originCity = scanner.nextLine();

		EditFlightRequest request = new EditFlightRequest(response.getReservation(), originCity);
		EditFlightResponse responseEdit = editFlightService.executeOriginCityUpdate(request);


		if (responseEdit.hasErrors()) {
			responseEdit.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println("Tickets departure city updated");
	}

	public static void reservationEdit_printEditMenu (long id) {
		BaseFunc.printHeader("EDIT TICKET: " + id);
		System.out.println(
				"[1] Departure city\n" +
						"[2] Destination city\n" +
						"[3] Departure date\n" +
						"[4] ARRIVAL date\n" +
						"[5] Seat\n" +
						"[0] Back");
	}

	private static Ticket fillTicket () {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter departure city: ");
		String departure = scanner.nextLine();


		System.out.println("Enter destination city: ");
		String destination = scanner.nextLine();
		destination = destination.trim();

		System.out.println("Enter departureDate: ");
		String departureDate = scanner.nextLine();
		departureDate = departureDate.trim();

		System.out.println("Enter returnDate: ");
		String returnDate = scanner.nextLine();
		returnDate = returnDate.trim();

		System.out.println("Enter seat: ");
		String seat = scanner.nextLine();
		seat = seat.trim();

		return new Ticket(departure, destination, departureDate, returnDate, seat);
	}
}
