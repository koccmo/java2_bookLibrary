package book_library.core.services;

import book_library.core.domain.Book;
import book_library.core.database.Database;
import book_library.core.requests.GetAllBooksRequest;
import book_library.core.responses.GetAllBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBooksService {

    @Autowired
    private Database database;

    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = database.getAllBooks();
        return new GetAllBooksResponse(books);
    }
}
