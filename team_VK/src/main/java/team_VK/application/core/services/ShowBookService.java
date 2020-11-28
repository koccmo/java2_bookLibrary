package team_VK.application.core.services;

import team_VK.application.database.Database;

public class ShowBookService {

    final Database database;

    public ShowBookService(Database database) {
        this.database = database;
    }

    public void showBook(long showingBookID) {
        database.getListBooks().stream()
                .filter(book -> book.ID == showingBookID)
                .findFirst()
                .ifPresent(book -> System.out.println(book.toString()));

    }
}
