package application_target_list.services;

import application_target_list.database.Database;

public class ChangeTargetDescriptionService {
    private Database database;

    public ChangeTargetDescriptionService(Database database){
        this.database = database;
    }

    public void execute(Long id, String newDescription){
        database.changeTargetDescription(id, newDescription);
    }
}
