package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.ShowTicketsRequest;
import lv.javaguru.app.core.response.ShowTicketResponse;
import lv.javaguru.app.core.services.ShowReservationsService;


public class ShowReservationsAction extends Action implements UIActions {

	private final ShowReservationsService showReservationsService;

	public ShowReservationsAction (ShowReservationsService showReservationsService) {
		this.showReservationsService = showReservationsService;
	}

	@Override
	public void execute () {
		User currUser = getLoggedInUser();

		ShowTicketsRequest request = new ShowTicketsRequest(currUser);
		ShowTicketResponse<?> response = showReservationsService.execute(request);

		if (response.hasErrors()) {
			System.out.println("Error list:");
		}
		else {
			System.out.println("Ticket list:");
		}

		response.printResponse();
	}
}
