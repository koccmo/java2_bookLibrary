package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.ShowFlightRequest;
import lv.javaguru.app.core.response.ShowFlightResponse;
import lv.javaguru.app.core.services.ShowFlightService;


public class ShowFlightsAction extends Action implements UIActions {

	private final ShowFlightService showFlightService;

	public ShowFlightsAction (ShowFlightService showFlightService) {
		this.showFlightService = showFlightService;
	}

	@Override
	public void execute () {
		User currUser = getLoggedInUser();

		ShowFlightRequest request = new ShowFlightRequest(currUser);
		ShowFlightResponse<?> response = showFlightService.execute(request);

		if (response.hasErrors()) {
			System.out.println("Error list:");
		}
		else {
			System.out.println("Ticket list:");
		}

		response.printResponse();
	}
}
