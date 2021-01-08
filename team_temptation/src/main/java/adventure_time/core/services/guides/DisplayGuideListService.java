package adventure_time.core.services.guides;

import adventure_time.database.events.EventDatabase;
import adventure_time.core.domain.Events;

import java.util.List;

public class DisplayGuideListService {
    private final EventDatabase databaseEvents;

    public DisplayGuideListService(EventDatabase databaseEvents) {
        this.databaseEvents = databaseEvents;
    }

    public List<Events> getEventsList() {
        return databaseEvents.getEventsList();
    }

}
