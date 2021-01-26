package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.FlightShowOneRequest;
import lv.javaguru.app.core.response.FlightShowOneResponse;
import lv.javaguru.app.core.services.FlightShowOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightShowUsersAction extends Action implements UIActions {

	@Autowired
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
