package lesson_2.database;

import lesson_2.Event;

import java.util.List;

public interface Database {

    void add (Event event);

    boolean remove (String eventName);

    List<Event> getEventsList ();

}
