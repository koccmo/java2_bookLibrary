package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.utility.TicketFiller;
import lv.javaguru.app.core.services.FlightAddService;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.response.AddFlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightAddAction extends Action implements UIActions {

	@Autowired
	private FlightAddService flightAddService;


	@Override
	public void execute () {
		Ticket ticket = new Ticket();

		TicketFiller filler = new TicketFiller(ticket);

		if (!filler.fill()) {
			System.out.println("Canceled!");
			return;
		}

		User currUser = getLoggedInUser();

		AddFlightRequest request = new AddFlightRequest(currUser, ticket);
		AddFlightResponse response = flightAddService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			System.out.println("Your reservation was added to list.");
	}


}

