package lesson_2.services;

import lesson_2.Target;
import lesson_2.database.Database;

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
