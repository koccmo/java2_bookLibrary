package lesson_2.services;

import lesson_2.database.Database;

public class RemoveEventService {
    private final Database database;

    public RemoveEventService(Database database) {
        this.database = database;
    }

    public void removeEvent(String eventName) {
        if (database.remove(eventName))
            System.out.println("An event " + eventName + " was removed from list.");
        else System.out.println("An event " + eventName + " was not found.");
    }
}
