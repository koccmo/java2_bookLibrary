package application_target_list.services;

import application_target_list.database.Database;

public class ChangeTargetDeadlineService {

    private Database database;

    public ChangeTargetDeadlineService(Database database){
        this.database = database;
    }

    public void execute(Long id, int newDeadline){
        database.changeTargetDeadline(id, newDeadline);
    }
}
