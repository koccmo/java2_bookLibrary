package core.services;

import domain.Events;
import database.Database;

import java.util.List;

public class DisplayEventListService {
    private final Database database;

    public DisplayEventListService(Database database) {
        this.database = database;
    }

    public List<Events> getEventsList () {
        return database.getEventsList();
    }

}
