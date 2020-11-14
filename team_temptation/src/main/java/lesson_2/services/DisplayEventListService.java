package lesson_2.services;

import lesson_2.Event;
import lesson_2.database.Database;

import java.util.List;

public class DisplayEventListService {
    private final Database database;

    public DisplayEventListService(Database database) {
        this.database = database;
    }

    public List<Event> getEventsList () {
        return database.getEventsList();
    }

}
