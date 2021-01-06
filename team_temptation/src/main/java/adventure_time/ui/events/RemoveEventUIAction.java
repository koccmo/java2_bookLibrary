package adventure_time.ui.events;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.events.RemoveEventResponse;
import adventure_time.core.services.events.RemoveEventService;
import adventure_time.dependencies.DIComponent;
import adventure_time.dependencies.DIDependency;
import adventure_time.ui.UIAction;

import java.util.Scanner;

@DIComponent
public class RemoveEventUIAction implements UIAction {

    @DIDependency
    private RemoveEventService removeEventService;

//    public RemoveEventUIAction(RemoveEventService removeEventService) {
//        this.removeEventService = removeEventService;
//    }

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
