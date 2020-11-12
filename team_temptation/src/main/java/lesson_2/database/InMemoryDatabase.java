package lesson_2.database;

import lesson_2.Event ;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Database {

    private long idCounter = 1L;
    private List<Event> events = new ArrayList<>();


    @Override
    public void add(Event event) {
        event.setIdNumber(idCounter);
        events.add(event);
        idCounter++;
    }

    @Override
    public boolean remove (String eventName) {
        return getEventsList().removeIf(items -> items.getEventName().equals(eventName));
    }

    @Override
    public List<Event> getEventsList() {
        return events;
    }
}
