package lesson_2.database;

import lesson_2.Trip;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Database {

    private long idCounter = 1L;
    private List<Trip> trips = new ArrayList<>();


    @Override
    public void add(Trip trip) {
        trip.setIdNumber(idCounter);
        trips.add(trip);
        idCounter++;
    }

    @Override
    public void remove(Trip trip) {
        trips.remove(trip);
    }

    @Override
    public List<Trip> getTripList() {
        return trips;
    }
}
