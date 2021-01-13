package lv.javaguru.app.console_ui.edit_ticket;

import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.core.request.edit.EditTicketDestinationRequest;
import lv.javaguru.app.core.response.edit.EditTicketDestinationResponse;
import lv.javaguru.app.core.services.EditTicketService;

import java.util.Scanner;

public class EditTicket_Destination implements UIActions {
    private final EditTicketService editTicketService;
    private final Long id;

    public EditTicket_Destination(Long id, EditTicketService editTicketService) {
        this.id = id;
        this.editTicketService = editTicketService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new destination city: ");
        String destination = scanner.nextLine();

        EditTicketDestinationRequest editTicketDestinationRequest = new EditTicketDestinationRequest(id, destination);
        EditTicketDestinationResponse editTicketDestinationResponse = editTicketService.execute(editTicketDestinationRequest);

        if (editTicketDestinationResponse.hasErrors()) {
            editTicketDestinationResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        } else
            System.out.println("Tickets destination city updated");
    }
}
