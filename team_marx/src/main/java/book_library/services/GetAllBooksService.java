package book_library.services;

import book_library.Book;
import book_library.database.Database;

import java.util.List;

public class GetAllBooksService {

    private Database database;

    public GetAllBooksService(Database database) {
        this.database = database;
    }

    public List<Book> execute() {
        return database.getAllBooks();
    }
}
