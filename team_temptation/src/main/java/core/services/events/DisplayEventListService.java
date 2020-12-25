package core.services.events;

import domain.Events;
import database.events.EventDatabase;

import java.util.List;

public class DisplayEventListService {
    private final EventDatabase databaseEvents;

    public DisplayEventListService(EventDatabase databaseEvents) {
        this.databaseEvents = databaseEvents;
    }

    public List<Events> getEventsList () {
        return databaseEvents.getEventsList();
    }

}
