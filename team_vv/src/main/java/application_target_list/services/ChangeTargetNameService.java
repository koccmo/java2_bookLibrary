package application_target_list.services;

import application_target_list.database.Database;

public class ChangeTargetNameService {

    private Database database;

    public ChangeTargetNameService(Database database){
        this.database = database;
    }

    public void execute(Long id, String newName ){
        database.changeTargetName(id, newName);
    }
}
