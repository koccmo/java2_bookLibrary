package database.events;

import core.requests.events.SearchEventRequest;
import domain.Events;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class InMemoryEvents implements EventDatabase {

    private Long idCounter = 1L;
    private final List<Events> events = new ArrayList<>();


    @Override
    public boolean add(Events event) {
        if (!events.isEmpty()) {
            for (Events item : events) {
                if (item.getEventName().equals(event.getEventName())) return false;
            }
        }
        event.setEventId(idCounter);
        events.add(event);
        idCounter++;
        return true;
    }

    @Override
    public boolean removeByName(String eventName) {
        return getEventsList().removeIf(items -> items.getEventName().equals(eventName));
    }

    @Override
    public boolean removeById(Long id) {
        return getEventsList().removeIf(items -> items.getEventId().equals(id));
    }

    @Override
    public List<Events> getEventsList() {
        return events;
    }

    @Override
    public List<Events> findByEventKind(SearchEventRequest request) {
        return events.stream()
                .filter(item -> item.getEventKind().equalsIgnoreCase(request.getEventKind()))
                .collect(toList());
    }
    @Override
    public List<Events> findByEventDuration(SearchEventRequest request) {
        return events.stream()
                .filter(item -> item.getDurationHours() <= request.getDurationHours())
                .collect(toList());
    }

    @Override
    public List<Events> findByRoute(SearchEventRequest request) {
        return events.stream()
                .filter(item -> item.getRoute().contains(request.getRoute()))
                .collect(toList());
    }

    @Override
    public List<Events> findByEventKindAndDuration(SearchEventRequest request) {
        return events.stream()
                .filter(item -> item.getEventKind().equalsIgnoreCase(request.getEventKind()))
                .filter(item -> item.getDurationHours() <= request.getDurationHours())
                .collect(toList());
    }

    @Override
    public List<Events> findByEventKindAndRoute(SearchEventRequest request) {
        return events.stream()
                .filter(item -> item.getEventKind().equalsIgnoreCase(request.getEventKind()))
                .filter(item -> item.getRoute().contains(request.getRoute()))
                .collect(toList());
    }

    @Override
    public List<Events> findByEventDurationAndRoute(SearchEventRequest request) {
        return events.stream()
                .filter(item -> item.getDurationHours() <= request.getDurationHours())
                .filter(item -> item.getRoute().contains(request.getRoute()))
                .collect(toList());
    }

    @Override
    public List<Events> findByEventKindAndDurationAndRoute(SearchEventRequest request) {
        return events.stream()
                .filter(item -> item.getEventKind().equalsIgnoreCase(request.getEventKind()))
                .filter(item -> item.getDurationHours() <= request.getDurationHours())
                .filter(item -> item.getRoute().contains(request.getRoute()))
                .collect(toList());
    }

}
