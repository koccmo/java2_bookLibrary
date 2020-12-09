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
        System.out.println("Enter an event name: ");
        String eventName = scanner.nextLine();

        RemoveEventRequest request = new RemoveEventRequest(eventName);
        RemoveEventResponse response = removeEventService.removeEvent(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("The event " + eventName + " was removed from  list.");
        }
    }
}
