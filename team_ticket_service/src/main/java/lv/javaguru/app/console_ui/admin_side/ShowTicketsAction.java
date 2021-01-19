package lv.javaguru.app.console_ui.admin_side;

import lv.javaguru.app.console_ui.Action;
import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.ShowTicketsRequest;
import lv.javaguru.app.core.response.ShowTicketResponse;
import lv.javaguru.app.core.services.ShowReservationsService;

public class ShowTicketsAction extends Action implements UIActions {
	//private final ShowTicketsService showTicketsService;
	private final ShowReservationsService showReservationsService;

	public ShowTicketsAction (ShowReservationsService showReservationsService) {
		this.showReservationsService = showReservationsService;
	}

	@Override
	public void execute () {
		User admin = getLoggedInUser();

		ShowTicketsRequest request = new ShowTicketsRequest(admin);
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
