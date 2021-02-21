package adventure_time.core.database.events;

import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.SearchEventRequest;

import java.util.List;
import java.util.Optional;

public interface BasicEventRepository {

    boolean add (Events event);
}
