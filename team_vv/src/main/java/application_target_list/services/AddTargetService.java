package application_target_list.services;

import application_target_list.Target;
import application_target_list.database.Database;

public class AddTargetService {

    private Database database;

    public AddTargetService(Database database){
        this.database = database;
    }

    public void execute(String name, String description, int deadline){
        Target target = new Target(name, description, deadline);
        database.addTarget(target);
    }
}
