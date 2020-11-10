package team_VK.application.services;

import team_VK.application.Book;
import team_VK.application.database.Database;

import java.util.List;

public class GetBooksListService {

    private final Database database;

    public GetBooksListService(Database database) {
        this.database = database;
    }

    public List<Book> getBooksList (){
        return database.getListBooks();
    }
}
