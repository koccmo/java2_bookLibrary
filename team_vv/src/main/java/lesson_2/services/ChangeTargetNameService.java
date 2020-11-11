package lesson_2.services;

import lesson_2.database.Database;

public class ChangeTargetNameService {

    private Database database;

    public ChangeTargetNameService(Database database){
        this.database = database;
    }

    public void execute(Long id, String newName ){
        database.changeTargetName(id, newName);
    }
}
