package lesson_2.services;

import lesson_2.database.Database;

public class ChangeTargetDescriptionService {
    private Database database;

    public ChangeTargetDescriptionService(Database database){
        this.database = database;
    }

    public void execute(Long id, String newDescription){
        database.changeTargetDescription(id, newDescription);
    }
}
