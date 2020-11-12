package lesson_2.services;

import lesson_2.Target;
import lesson_2.database.Database;

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
