package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.response.DeleteFlightResponse;
import lv.javaguru.app.core.services.FlightDeleteService;

public class FlightDeleteAction implements UIActions {

    private final FlightDeleteService flightDeleteService;

    public FlightDeleteAction (FlightDeleteService flightDeleteService) {
        this.flightDeleteService = flightDeleteService;
    }

    @Override
    public void execute() {
        BaseFunc.printHeader("Enter ticket ID:");
        long id = BaseFunc.getMenuNumberFromUser();

        DeleteFlightRequest request = new DeleteFlightRequest(id);
        DeleteFlightResponse response = flightDeleteService.execute(request);

        if (response.hasErrors()) {
            response.getErrorList().forEach(System.out::println);
        }
        else{
            System.out.println(response.getMessage());
        }

    }




}
