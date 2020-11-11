package lesson_2.services;

import lesson_2.database.Database;

public class RemoveTripService {
    private final Database database;

    public RemoveTripService(Database database) {
        this.database = database;
    }

    public void removeTrip(String tripName) {
        if (database.getTripList().removeIf(items -> items.getTripName().equals(tripName)))
            System.out.println("A trip " + tripName + " was removed from list.");
        else System.out.println("A trip " + tripName + " was not found.");
    }
}
