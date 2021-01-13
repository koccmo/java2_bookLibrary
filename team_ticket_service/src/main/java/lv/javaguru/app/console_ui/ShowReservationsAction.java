package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.ShowTicketsRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.ShowTicketResponse;
import lv.javaguru.app.core.services.ShowReservationsService;

import java.util.List;

public class ShowReservationsAction extends Action implements UIActions {

	private final ShowReservationsService showReservationsService;

	public ShowReservationsAction (ShowReservationsService showReservationsService) {
		this.showReservationsService = showReservationsService;
	}

	@Override
	public void execute () {
		Person currUser = getLoggedInUser();

		ShowTicketsRequest request = new ShowTicketsRequest(currUser);
		ShowTicketResponse response = showReservationsService.execute(request);

		//response.getResponse().getClass();
		if (!response.getResponse().isEmpty())
			response.getResponse().forEach(System.out::println);
		//else
		//	System.out.println(response.getTicketsAsString());
	}
}
