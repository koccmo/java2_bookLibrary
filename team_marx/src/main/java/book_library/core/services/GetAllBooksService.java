package book_library.core.services;

import book_library.Book;
import book_library.core.database.Database;
import book_library.core.requests.GetAllBooksRequest;
import book_library.core.responses.GetAllBooksResponse;
import book_library.dependency_injection.DIComponent;
import book_library.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class GetAllBooksService {

    @DIDependency private Database database;

    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = database.getAllBooks();
        return new GetAllBooksResponse(books);
    }
}
