package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.request.FlightShowOneRequest;
import lv.javaguru.app.core.response.FlightShowAllResponse;
import lv.javaguru.app.core.response.FlightShowOneResponse;
import lv.javaguru.app.core.services.FlightShowAllService;
import lv.javaguru.app.core.services.FlightShowOneService;


public class FlightShowUsersAction extends Action implements UIActions {

	private final FlightShowOneService flightShowOneService;

	public FlightShowUsersAction (FlightShowOneService flightShowOneService) {
		this.flightShowOneService = flightShowOneService;
	}

	@Override
	public void execute () {
		System.out.println("Enter flight ID:");
		long id = BaseFunc.getMenuNumberFromUser();


		FlightShowOneRequest request = new FlightShowOneRequest(id);
		FlightShowOneResponse response = flightShowOneService.execute(request);

		System.out.println(response.getFlight());
	}
}
