package adventure_time.database.events;

import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.requests.events.SearchEventRequest;
import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.UpdateEventRequest;

import java.util.List;
import java.util.Optional;

public interface EventDatabase {

    boolean add (Events event);

    boolean removeByName (String eventName);

    boolean removeById (Long id);

    List<Events> getEventsList ();

    Optional<Events> findById (Long id);

    boolean updateEvent (Events event);

    List<Events> findByEventKind (SearchEventRequest request);
    List<Events> findByEventKindAndRoute (SearchEventRequest request);
    List<Events> findByEventKindAndDuration (SearchEventRequest request);
    List<Events> findByEventKindAndDurationAndRoute (SearchEventRequest request);
    List<Events> findByEventDuration (SearchEventRequest request);
    List<Events> findByEventDurationAndRoute (SearchEventRequest request);
    List<Events> findByRoute (SearchEventRequest request);

}
