package team_VK.application.core.services.main_menu_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.database.Database;

@Component
public class ShowBookService {

    @Autowired
    Database database;

    public void showBook(long showingBookID) {
        database.getListBooks().stream()
                .filter(book -> book.ID == showingBookID)
                .findFirst()
                .ifPresent(book -> System.out.println(book.toString()));

    }
}
