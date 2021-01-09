package adventure_time.ui.events;

import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.requests.events.UpdateEventRequest;
import adventure_time.core.responses.events.UpdateEventResponse;
import adventure_time.core.services.events.UpdateEventService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

@Component
public class UpdateUIAction implements UIAction {

    @Autowired
    private UpdateEventService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID number of the event: ");
        Long eventId = scanner.nextLong();

        UpdateEventRequest requestOne = new UpdateEventRequest(eventId);
        UpdateEventResponse response = service.findById(requestOne);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
            System.out.println("");

        } else {
            Events event = response.getEvent();
            System.out.println("Here is the event you want to change: ");
            System.out.println(response.getEvent().toString());
            System.out.println();

            Long id = event.getEventId();

            String eventName = event.getEventName();
            System.out.println("Would you like to change the event name (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                System.out.println("Enter an event name: ");
                eventName = scanner.nextLine();
            }

            String eventKind = event.getEventKind();
            System.out.println("Would you like to change the event kind (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                System.out.println("Enter a kind of the event: B - bike trip, T - boat trip, W - walking trip, " +
                        "M - motorcycle trip, U - bus trip.");
                eventKind = switch (scanner.nextLine()) {
                    case "B" ->  "bike trip";
                    case "T" ->  "boat trip";
                    case "W" ->  "walking trip";
                    case "M" ->  "motorcycle trip";
                    case "U" ->  "bus trip";
                    default -> "undefined";
                };
            }

            Integer durationHours = event.getDurationHours();
            System.out.println("Would you like to change the event trip duration (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                System.out.println("Enter a trip duration (in hours): ");
                durationHours = parseInt((scanner.nextLine()));
            }

            Integer maxNumberParticipants = event.getMaxNumberParticipants();
            System.out.println("Would you like to change the event max number of participant (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                System.out.println("Enter a max number of participant: ");
                maxNumberParticipants = parseInt(scanner.nextLine());
            }

            Integer minNumberParticipants = event.getMinNumberParticipants();
            System.out.println("Would you like to change the event min number of participant (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                System.out.println("Enter a min number of participant: ");
                minNumberParticipants = parseInt(scanner.nextLine());
            }

            String route = event.getRoute();
            System.out.println("Would you like to change the event route of the trip (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                System.out.println("Define a route of the trip: ");
                route = scanner.nextLine();
            }


            String detailDescription = event.getDetailsDescription();
//TODO check the possibility of such refactoring implementation in tests
//            // 3-d edition
//            detailDescription = ret (getNewValue("event description"), detailDescription);
//            // 2-nd edition
//            String value = getNewValue("event description");
//            if (value != null) detailDescription = value;
            System.out.println("Would you like to change the event description (Y/N)? ");
            if (scanner.nextLine().equals("Y")) {
                System.out.println("Enter an event description: ");
                detailDescription = scanner.nextLine();
            }

            AddEventRequest requestTwo = new AddEventRequest(eventName, eventKind, durationHours,
                    maxNumberParticipants, minNumberParticipants,
                    route, detailDescription);

            response = service.updateEvent(requestTwo, id);

            if (response.hasError()) {
                response.getErrors().forEach(System.out::println);
            } else {
                System.out.println("The event " + id + " was updated.");
            }
        }
    }
//TODO check the possibility of such refactoring implementation in tests
//    private String ret (String rez, String val) {
//        return rez == null
//                ? val
//                : rez;
//    }
//  private String getNewValue (String message) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Would you like to change the " + message + " (Y/N)? ");
//        if (scanner.nextLine().equals("Y")) {
//            System.out.println("Enter a new " + message + ": ");
//            return scanner.nextLine();
//        }
//        return null;
//    }
}
