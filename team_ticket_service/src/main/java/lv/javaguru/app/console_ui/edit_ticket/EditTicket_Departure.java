package lv.javaguru.app.console_ui.edit_ticket;

import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.core.request.edit.EditTicketDepartureRequest;
import lv.javaguru.app.core.response.edit.EditTicketDepartureResponse;
import lv.javaguru.app.core.services.EditReservationService;

import java.util.Scanner;

public class EditTicket_Departure implements UIActions {

    private final EditReservationService editReservationService;
    private final Long id;

    public EditTicket_Departure(Long id, EditReservationService editReservationService) {
        this.id = id;
        this.editReservationService = editReservationService;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new departure city: ");
        String departure = scanner.nextLine();

        EditTicketDepartureRequest editTicketDepartureRequest = new EditTicketDepartureRequest(id, departure);
        EditTicketDepartureResponse editTicketDepartureResponse = editReservationService.execute(editTicketDepartureRequest);

        if (editTicketDepartureResponse.hasErrors()) {
            editTicketDepartureResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        } else
            System.out.println("Ticket departure city updated");
    }
}
