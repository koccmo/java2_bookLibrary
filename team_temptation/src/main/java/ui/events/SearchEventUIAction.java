package ui.events;

import core.requests.Ordering;
import core.requests.Paging;
import core.requests.events.SearchEventRequest;
import core.responses.events.SearchEventResponse;
import core.services.events.SearchEventService;
import ui.UIAction;

import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class SearchEventUIAction implements UIAction {

    private final SearchEventService searchEventService;

    public SearchEventUIAction(SearchEventService searchEventService) {
        this.searchEventService = searchEventService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Define a kind of the event: B - bike trip, T - boat trip, W - walking trip, M - motorcycle trip, U - bus trip.");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String eventKind = switch (scanner.nextLine()) {
            case "B" ->  "bike trip";
            case "T" ->  "boat trip";
            case "W" ->  "walking trip";
            case "M" ->  "motorcycle trip";
            case "U" ->  "bus trip";
            default -> "";
        };

        System.out.println("Define a trip route. Enter one point of the route.");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String route = scanner.nextLine();

        Integer durationHours = Integer.valueOf(0);
        System.out.println("Define a trip duration in hours (not more than).");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String str = scanner.nextLine();
        if (!str.equals("")) durationHours = Integer.parseInt(str);

        System.out.println("Define the sorting criteria: N - by event name, R - by route, D - by duration.");
        System.out.println("If the sorting doesn't need just press Enter:");
        String orderBy = switch (scanner.nextLine()) {
            case "N" ->  "eventNamed";
            case "R" ->  "route";
            case "D" ->  "durationHours";
            default -> "";
        };
        System.out.println("Define the sorting order: A - ascending, D - descending.");
        System.out.println("If the order doesn't matter just press Enter:");
        String orderDirection = switch (scanner.nextLine()) {
            case "D" ->  "descending";
            case "A" ->  "ascending";
            default -> "";
        };
        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Define a displayed items number (min 8, max 30).");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        System.out.println("Define a page number.");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        SearchEventRequest request = new SearchEventRequest(eventKind, route, durationHours, ordering, paging);
        SearchEventResponse response = searchEventService.searchEvent(request);

        if (response.hasError()) {
            System.out.println("Your request is not correct cause of:");
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Here are list of events under your request:");
            response.getEvents().forEach(System.out::println);
        }
    }
}