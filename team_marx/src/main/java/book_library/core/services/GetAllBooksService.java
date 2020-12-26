package book_library.core.services;

import book_library.Book;
import book_library.core.database.Database;
import book_library.core.requests.GetAllBooksRequest;
import book_library.core.responses.GetAllBooksResponse;

import java.util.List;

public class GetAllBooksService {

    private Database database;

    public GetAllBooksService(Database database) {
        this.database = database;
    }

    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = database.getAllBooks();
        return new GetAllBooksResponse(books);
    }
}
