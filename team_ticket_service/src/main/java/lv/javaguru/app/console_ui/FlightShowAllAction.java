package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.response.FlightShowAllResponse;
import lv.javaguru.app.core.services.FlightShowAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightShowAllAction extends Action implements UIActions {

	@Autowired
	private FlightShowAllService flightShowAllService;


	@Override
	public void execute () {
		User currUser = getLoggedInUser();

		FlightShowAllRequest request = new FlightShowAllRequest(currUser);
		FlightShowAllResponse<?> response = flightShowAllService.execute(request);

		if (response.hasErrors()) {
			System.out.println("Error list:");
		}
		else {
			System.out.println("Ticket list:");
		}

		response.printResponse();
	}
}
