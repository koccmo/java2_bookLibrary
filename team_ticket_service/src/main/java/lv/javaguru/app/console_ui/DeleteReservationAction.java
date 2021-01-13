package lv.javaguru.app.console_ui;

import lv.javaguru.app.Main;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.DeleteReservationRequest;
import lv.javaguru.app.core.response.DeleteReservationResponse;
import lv.javaguru.app.core.services.DeleteReservationService;
import lv.javaguru.app.core.domain.Person;

import java.util.Scanner;

public class DeleteReservationAction implements UIActions {

    private final DeleteReservationService deleteReservationService;

    public DeleteReservationAction(DeleteReservationService deleteReservationService) {
        this.deleteReservationService = deleteReservationService;
    }

    @Override
    public void execute() {
        System.out.println("Enter ticket ID: ");
        long id = BaseFunc.getMenuNumberFromUser();

        DeleteReservationRequest request = new DeleteReservationRequest(id);
        DeleteReservationResponse response = deleteReservationService.execute(request);

        if (response.hasErrors()) {
            response.getErrorList().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
        else{


            System.out.println(response.getMessage());
        }

    }




}
