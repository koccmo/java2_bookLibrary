package team_VK.application.core.services.admin_services;

import team_VK.application.database.database_Admin.Database;

public class RemoveBookService {

    private final Database database ;
    public RemoveBookService(Database database) {
        this.database = database;
    }

    public void removeBook (long ID){
    database.getListBooks().stream()
            .filter(book -> book.getID() == ID)
            .findFirst()
            .ifPresent(book -> database.getListBooks().remove(book));
    }
}