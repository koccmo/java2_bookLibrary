package internet_store.application.services;

import internet_store.application.database.Database;

public class ChangeProductNameService {

    private final Database database;

    public ChangeProductNameService(Database database) {
        this.database = database;
    }

    public boolean changeProductName(Long id, String newName) {
        return database.changeProductName(id, newName);
    }
}
