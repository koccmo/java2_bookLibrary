package internet_store_1.lesson_2.services;

import internet_store_1.lesson_2.database.Database;

public class ChangeProductNameService {

    private final Database database;

    public ChangeProductNameService(Database database) {
        this.database = database;
    }

    public boolean changeProductName(Long id, String newName) {
        return database.changeProductName(id, newName);
    }
}
