package internet_store.core.services;

import internet_store.core.database.Database;

public class ChangeProductNameService {

    private final Database database;

    public ChangeProductNameService(Database database) { this.database = database; }

    public boolean changeProductName(Long id, String newName) { return database.changeProductName(id, newName); }
}
