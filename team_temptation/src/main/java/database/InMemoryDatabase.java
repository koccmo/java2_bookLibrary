package database;

import domain.Events;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Database {

    private long idCounter = 1L;
    private List<Events> events = new ArrayList<>();


    @Override
    public void add(Events event) {
        event.setEventId(idCounter);
        events.add(event);
        idCounter++;
    }

    @Override
    public boolean remove (String eventName) {
        return getEventsList().removeIf(items -> items.getEventName().equals(eventName));
    }

    @Override
    public List<Events> getEventsList() {
        return events;
    }
}
