package ui.events;

import core.requests.events.AddEventRequest;
import core.responses.events.AddEventResponse;
import core.services.events.AddEventService;
import ui.UIAction;

import java.util.Scanner;

import static java.lang.Integer.*;

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

        System.out.println("Enter a kind of the event: B - bike trip, T - boat trip, W - walking trip, M - motorcycle trip, U - bus trip.");
        String eventKind = switch (scanner.nextLine()) {
            case "B" ->  "bike trip";
            case "T" ->  "boat trip";
            case "W" ->  "walking trip";
            case "M" ->  "motorcycle trip";
            case "U" ->  "bus trip";
            default -> "undefined";
        };

        System.out.println("Enter a trip duration (in hours): ");
        Integer durationHours = parseInt((scanner.nextLine()));

        System.out.println("Enter a max number of participant: ");
        Integer maxNumberParticipants = parseInt(scanner.nextLine());

        System.out.println("Enter a min number of participant: ");
        Integer minNumberParticipants = parseInt(scanner.nextLine());

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