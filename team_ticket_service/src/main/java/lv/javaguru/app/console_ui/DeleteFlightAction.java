package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.response.DeleteFlightResponse;
import lv.javaguru.app.core.services.DeleteFlightService;

public class DeleteFlightAction implements UIActions {

    private final DeleteFlightService deleteFlightService;

    public DeleteFlightAction (DeleteFlightService deleteFlightService) {
        this.deleteFlightService = deleteFlightService;
    }

    @Override
    public void execute() {
        BaseFunc.printHeader("Enter ticket ID:");
        long id = BaseFunc.getMenuNumberFromUser();

        DeleteFlightRequest request = new DeleteFlightRequest(id);
        DeleteFlightResponse response = deleteFlightService.execute(request);

        if (response.hasErrors()) {
            response.getErrorList().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
        else{
            System.out.println(response.getMessage());
        }

    }




}
