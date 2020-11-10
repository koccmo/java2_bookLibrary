package team_VK.application.services;

import team_VK.application.Book;
import team_VK.application.database.Database;

import java.util.function.Predicate;

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






