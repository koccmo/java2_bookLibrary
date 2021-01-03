package core.services.events;

import core.requests.Ordering;
import core.requests.Paging;
import core.requests.events.SearchEventRequest;
import core.responses.CoreError;
import core.responses.events.SearchEventResponse;
import database.events.EventDatabase;
import domain.Events;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class SearchEventService {

    private final EventDatabase database;
    private final SearchEventRequestValidator validator;

    public SearchEventService(EventDatabase database, SearchEventRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchEventResponse searchEvent (SearchEventRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchEventResponse(null, errors);   // validation failed
        }

//      the search request is valid & must be passed to the DB for execution
        List<Events> events = search(request); // events - list of items matching the request

        if (events.isEmpty()) {
            errors.add(new CoreError(null,"There are no items in the database for this request"));
            return new SearchEventResponse(null, errors);
        }

        // here is sorting
        events = order (events, request.getOrdering());

        // here is paging
        events = paging (events, request.getPaging());

        return new SearchEventResponse(events, null);
    }
    private List<Events> search(SearchEventRequest request) {
        List<Events> events = new ArrayList<>();

        if (request.isKind()) events = database.findByEventKind(request);
        if (request.isDuration()) events = database.findByEventDuration(request);
        if (request.isRoute()) events = database.findByRoute(request);
        if (request.isKindAndDuration()) events = database.findByEventKindAndDuration(request);
        if (request.isKindAndRoute()) events = database.findByEventKindAndRoute(request);
        if (request.isDurationAndRoute()) events = database.findByEventDurationAndRoute(request);
        if (request.isKindAndDurationAndRout()) events = database.findByEventKindAndDurationAndRoute(request);
        if (request.isNoOne()) events = database.getEventsList();

        return events;
    }

    //TODO consider implementing this as a static-method in Paging-class m.b. or new class like
    // an "Utilities" to make it available/reachable for further usage in DisplayEventUIAction
    private List<Events> paging(List<Events> events, Paging paging) {

//        int lastPageNumber = events.size()/paging.getPageSize();
//        if (events.size() % paging.getPageSize() > 0) lastPageNumber++;
        int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
//        if (lastPageNumber < paging.getPageSize()) skip = (lastPageNumber - 1) * paging.getPageSize();
        return events.stream()
                .skip(skip)
                .limit(paging.getPageSize())
                .collect(Collectors.toList());
    }

    private List<Events> order(List<Events> events, Ordering order) {

        if (order.isAscending("eventNamed")) return sortEventNameAscending(events);

        if (order.isDescending("eventNamed")) return sortEventNameDescending(events);

        if (order.isDescending("route")) return sortRouteDescending(events);

        if (order.isAscending("route")) return sortRouteAscending(events);

        if (order.isAscending("durationHours")) return sortDurationHoursAscending(events);

        if (order.isDescending("durationHours")) return sortDurationHoursDescending(events);
        return events;
    }

    private List<Events> sortEventNameAscending(List<Events> events) {
        return events.stream()
                .sorted(comparing(Events::getEventName))
                .collect(toList());
    }

    private List<Events> sortEventNameDescending(List<Events> events) {
        return events.stream()
                .sorted((item1, item2) -> -item1.getEventName().compareTo(item2.getEventName()))
                .collect(toList());
    }

    private List<Events> sortRouteDescending(List<Events> events) {
        return events.stream()
                .sorted((item1, item2) -> -item1.getRoute().compareTo(item2.getRoute()))
                .collect(toList());
    }

    private List<Events> sortRouteAscending(List<Events> events) {
        return events.stream()
                .sorted(comparing(Events::getRoute))
                .collect(toList());
    }

    private List<Events> sortDurationHoursAscending(List<Events> events) {
        return events.stream()
                .sorted(comparing(Events::getDurationHours))
                .collect(toList());
    }

    private List<Events> sortDurationHoursDescending(List<Events> events) {
        return events.stream()
                .sorted((item1, item2) -> -item1.getDurationHours().compareTo(item2.getDurationHours()))
                .collect(toList());
    }

}
