package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.core.services.FlightDeleteService;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

@DIComponent
public class FlightDeleteAction implements UIActions {

	@DIDependency
	private FlightDeleteService flightDeleteService;


	@Override
	public void execute () {
		BaseFunc.printHeader("Enter ticket ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		DeleteFlightRequest request = new DeleteFlightRequest(id);
		FlightDeleteResponse response = flightDeleteService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(System.out::println);
		}
		else {
			System.out.println(response.getMessage());
		}

	}


}
