package lesson_2.services;

import lesson_2.database.Database;

public class DeleteTargetService {
    private Database database;

    public DeleteTargetService(Database database){
        this.database = database;
    }

    public void execute(Long id){
        database.deleteTarget(id);
    }
}
