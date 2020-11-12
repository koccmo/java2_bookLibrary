package lesson_2.services;

import lesson_2.Trip;
import lesson_2.database.Database;

public class AddTripService {

    private final Database database;

    public AddTripService(Database database) {
        this.database = database;
    }

    public void addTrip (String tripName, String startDate, String finishDate, String detailDescription) {
        Trip trip = new Trip(tripName, startDate, finishDate, detailDescription);
        database.add(trip);
    }
}
