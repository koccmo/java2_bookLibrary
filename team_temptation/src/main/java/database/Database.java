package database;

import domain.Events;

import java.util.List;

public interface Database {

    void add (Events event);

    boolean remove (String eventName);

    List<Events> getEventsList ();

}
