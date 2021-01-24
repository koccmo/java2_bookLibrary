package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.request.FlightShowOneRequest;
import lv.javaguru.app.core.response.FlightShowAllResponse;
import lv.javaguru.app.core.response.FlightShowOneResponse;
import lv.javaguru.app.core.services.FlightShowAllService;
import lv.javaguru.app.core.services.FlightShowOneService;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

@DIComponent
public class FlightShowUsersAction extends Action implements UIActions {

	@DIDependency
	private FlightShowOneService flightShowOneService;


	@Override
	public void execute () {
		System.out.println("Enter flight ID:");
		long id = BaseFunc.getMenuNumberFromUser();


		FlightShowOneRequest request = new FlightShowOneRequest(id);
		FlightShowOneResponse response = flightShowOneService.execute(request);

		System.out.println(response.getFlight());
	}
}
