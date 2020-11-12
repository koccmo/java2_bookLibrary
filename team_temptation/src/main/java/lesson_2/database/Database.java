package lesson_2.database;

import lesson_2.Trip;

import java.util.List;

public interface Database {

    void add (Trip trip);

    void remove (Trip trip);

    List<Trip> getTripList ();

}
