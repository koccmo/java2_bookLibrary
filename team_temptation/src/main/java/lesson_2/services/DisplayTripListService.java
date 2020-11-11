package lesson_2.services;

import lesson_2.Trip;
import lesson_2.database.Database;

import java.util.List;

public class DisplayTripListService {
    private final Database database;

    public DisplayTripListService (Database database) {
        this.database = database;
    }

    public List<Trip> getTripList () {
        return database.getTripList();
    }

}
