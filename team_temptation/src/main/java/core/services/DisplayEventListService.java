package core.services;

import domain.Event;
import database.Database;

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
