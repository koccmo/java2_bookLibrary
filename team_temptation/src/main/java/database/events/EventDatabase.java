package database.events;

import core.requests.events.SearchEventRequest;
import domain.Events;

import java.util.List;

public interface EventDatabase {

    boolean add (Events event);

    boolean removeByName (String eventName);

    boolean removeById (Long id);

    List<Events> getEventsList ();

    List<Events> findByEventKind (SearchEventRequest request);
    List<Events> findByEventKindAndRoute (SearchEventRequest request);
    List<Events> findByEventKindAndDuration (SearchEventRequest request);
    List<Events> findByEventKindAndDurationAndRoute (SearchEventRequest request);
    List<Events> findByEventDuration (SearchEventRequest request);
    List<Events> findByEventDurationAndRoute (SearchEventRequest request);
    List<Events> findByRoute (SearchEventRequest request);

}
