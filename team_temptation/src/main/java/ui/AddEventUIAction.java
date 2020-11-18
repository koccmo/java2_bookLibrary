package ui;

import core.requests.AddEventRequest;
import core.responses.AddEventResponse;
import core.services.AddEventService;

import java.util.Scanner;

public class AddEventUIAction implements UIAction {

    private final AddEventService addEventService;

    public AddEventUIAction(AddEventService addEventService) {
        this.addEventService = addEventService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an event name: ");
        String eventName = scanner.nextLine();
        System.out.println("Enter a date the event is starting: ");
        String startDate = scanner.nextLine();
        System.out.println("Enter a date the event finishes: ");
        String finishDate = scanner.nextLine();
        System.out.println("Enter an event description: ");
        String detailDescription = scanner.nextLine();
        AddEventRequest request = new AddEventRequest(eventName, startDate, finishDate, detailDescription);
        AddEventResponse response = addEventService.addEvent(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Your trip was added to list.");
        }

    }
}