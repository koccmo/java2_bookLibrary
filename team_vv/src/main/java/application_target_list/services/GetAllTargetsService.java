package application_target_list.services;

import application_target_list.Target;
import application_target_list.database.Database;

import java.util.List;


public class GetAllTargetsService {

    private Database database;

    public GetAllTargetsService(Database database) {
        this.database = database;
    }

    public List<Target> execute() {
        return database.getTargetsList();
    }
}
