package lesson_2.services;

import lesson_2.Event;
import lesson_2.database.Database;

public class AddEventService {

    private final Database database;

    public AddEventService(Database database) {
        this.database = database;
    }

    public void addEvent (String eventName, String startDate, String finishDate, String detailDescription) {
        Event event = new Event(eventName, startDate, finishDate, detailDescription);
        database.add(event);
    }
}
