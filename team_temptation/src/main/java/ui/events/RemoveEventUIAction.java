package ui.events;

import core.requests.events.RemoveEventRequest;
import core.responses.events.RemoveEventResponse;
import core.services.events.RemoveEventService;
import ui.UIAction;

import java.util.Scanner;

public class RemoveEventUIAction implements UIAction {

    private final RemoveEventService removeEventService;

    public RemoveEventUIAction(RemoveEventService removeEventService) {
        this.removeEventService = removeEventService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting...");
        System.out.println("Choose the way of the event deletion: by the name (press N) or by the ID-number (press I):");

        RemoveEventRequest request;

        if (scanner.nextLine().equals("N")) {
            System.out.println("Enter an event name: ");
            request = new RemoveEventRequest(scanner.nextLine(),  "byName");
        } else {
            System.out.println("Enter an event ID: ");
            request = new RemoveEventRequest(toLong(scanner.nextLine()), "byId");
        }

        RemoveEventResponse response = removeEventService.removeEvent(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.print("The event \"" + whatIsTheValueOFThisWay(request));
            System.out.println(endOfMessage(response));
        }
    }

    private String endOfMessage(RemoveEventResponse response) {
        return response.isSuccessRemoval()
                ? "\" removed from list."
                : "\" not found.";
    }

    private static String whatIsTheValueOFThisWay(RemoveEventRequest request) {
        return request.getDeletionWay().equals("byName")
                ? request.getEventName()
                : request.getEventId().toString();
    }

    private Long toLong (String byId) {
        return byId.isBlank()
                ? 0L
                : Long.parseLong(byId);
    }
}
