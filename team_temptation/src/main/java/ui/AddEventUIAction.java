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
        System.out.println("Enter a kind of the event:");
        System.out.println("1 - bike trip, 2 - boat trip, 3 - walking trip, 4 - motorcycle trip, 5  buss trip, 6 - other:");
        String eventKind = switch (scanner.nextInt()) {
            case 1 ->  "bike trip";
            case 2 ->  "boat trip";
            case 3 ->  "walking trip";
            case 4 ->  "motorcycle trip";
            case 5 ->  "buss trip";
            default -> "other";
        };
        System.out.println("Enter a trip duration (in hours): ");
        int durationHours = scanner.nextInt();
        System.out.println("Enter a max number of participant: ");
        int maxNumberParticipants = scanner.nextInt();
        System.out.println("Enter a min number of participant: ");
        int minNumberParticipants = scanner.nextInt();
        System.out.println("Define a route of the trip: ");
        String route = scanner.nextLine();
        System.out.println("Enter an event description: ");
        String detailDescription = scanner.nextLine();
        AddEventRequest request = new AddEventRequest(  eventName, eventKind, durationHours,
                                                        maxNumberParticipants, minNumberParticipants,
                                                        route, detailDescription);
        AddEventResponse response = addEventService.addEvent(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("The event " + eventName + " was added to list.");
        }

    }
}