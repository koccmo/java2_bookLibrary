package application_target_list.services;

import application_target_list.database.Database;

public class DeleteTargetService {
    private Database database;

    public DeleteTargetService(Database database){
        this.database = database;
    }

    public void execute(Long id){
        database.deleteTarget(id);
    }
}
