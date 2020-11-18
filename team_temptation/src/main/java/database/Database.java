package database;

import domain.Event;

import java.util.List;

public interface Database {

    void add (Event event);

    boolean remove (String eventName);

    List<Event> getEventsList ();

}
