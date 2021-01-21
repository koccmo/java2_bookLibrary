package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.edit_ticket.EditTicket_Departure;
import lv.javaguru.app.console_ui.edit_ticket.EditTicket_Destination;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.EditReservationRequest;
import lv.javaguru.app.core.request.edit.*;
import lv.javaguru.app.core.response.EditTicketResponse;
import lv.javaguru.app.core.response.edit.*;
import lv.javaguru.app.core.services.EditReservationService;

import java.util.Scanner;

public class EditTicketAction implements UIActions {

	private final EditReservationService editReservationService;

	public EditTicketAction (EditReservationService editReservationService) {
		this.editReservationService = editReservationService;
	}

	@Override
	public void execute () {
		System.out.println("Enter ticket ID: ");
		long id = BaseFunc.getMenuNumberFromUser();

		EditReservationRequest request = new EditReservationRequest(id);
		EditTicketResponse response = editReservationService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			userMode_TicketEditMenu(response.getId());
	}


	private void userMode_TicketEditMenu (long id) {
		while (true) {
			printUserMode_TicketEditSubMenu(id);
			int menuNumber = BaseFunc.getMenuNumberFromUser();
			switch (menuNumber) {
				case 1 -> {
					EditTicket_Departure editTicket_departure = new EditTicket_Departure(id, editReservationService);
					editTicket_departure.execute();
				}
				case 2 -> {
					EditTicket_Destination editTicket_destination = new EditTicket_Destination(id, editReservationService);
					editTicket_destination.execute();
				}
				case 3 -> {
					Scanner scanner = new Scanner(System.in);
					System.out.println("Enter new departure date: ");
					String departureDate = scanner.nextLine();

					EditTicketDepartureDateRequest editTicketDepartureDateRequest = new EditTicketDepartureDateRequest(id, departureDate);
					EditTicketDepartureDateResponse editTicketDepartureDateResponse = editReservationService.execute(editTicketDepartureDateRequest);

					if (editTicketDepartureDateResponse.hasErrors()) {
						editTicketDepartureDateResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					System.out.println("Tickets departure date updated");
				}
				case 4 -> {
					Scanner scanner = new Scanner(System.in);
					System.out.println("Enter new return date: ");
					String returnDate = scanner.nextLine();

					EditTicketArrivalDateRequest editTicketArrivalDateRequest = new EditTicketArrivalDateRequest(id, returnDate);
					EditTicketReturnDateResponse editTicketReturnDateResponse = editReservationService.execute(editTicketArrivalDateRequest);

					if (editTicketReturnDateResponse.hasErrors()) {
						editTicketReturnDateResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					System.out.println("Tickets arrival date updated");
				}
				case 5 -> {
					Scanner scanner = new Scanner(System.in);
					System.out.println("Enter new seat date: ");
					String seat = scanner.nextLine();

					EditTicketSeatRequest editTicketSeatRequest = new EditTicketSeatRequest(id, seat);
					EditTicketSeatResponse editTicketSeatResponse = editReservationService.execute(editTicketSeatRequest);

					if (editTicketSeatResponse.hasErrors()) {
						editTicketSeatResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
								r.getMessage()));
					}
					System.out.println("Tickets arrival date updated");
				}
				case 0 -> {
					return;
				}
			}

		}
	}

	public static void printUserMode_TicketEditSubMenu (long id) {
		BaseFunc.printHeader("EDIT TICKET: " + id);
		BaseFunc.printLineSeparator();
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
