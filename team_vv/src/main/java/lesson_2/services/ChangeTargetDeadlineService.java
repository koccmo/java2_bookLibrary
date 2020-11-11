package lesson_2.services;

import lesson_2.database.Database;

public class ChangeTargetDeadlineService {

    private Database database;

    public ChangeTargetDeadlineService(Database database){
        this.database = database;
    }

    public void execute(Long id, int newDeadline){
        database.changeTargetDeadline(id, newDeadline);
    }
}
