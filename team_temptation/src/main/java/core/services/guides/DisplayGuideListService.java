package core.services.guides;

import database.events.EventDatabase;
import domain.Events;

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
