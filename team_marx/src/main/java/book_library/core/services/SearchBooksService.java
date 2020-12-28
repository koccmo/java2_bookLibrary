package book_library.core.services;

import book_library.Book;
import book_library.core.database.Database;
import book_library.core.requests.SearchBooksRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.SearchBooksResponse;
import book_library.core.validators.SearchBooksRequestValidator;

import java.util.List;

public class SearchBooksService {

    private Database database;
    private SearchBooksRequestValidator validator;

    public SearchBooksService(Database database, SearchBooksRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchBooksResponse execute (SearchBooksRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new SearchBooksResponse(null, errors);
        }

        List<Book> books = null;
        if (request.isTitleProvided() && !request.isAuthorProvided()){
            books = database.findByTitle(request.getTitle());
        }
        if (!request.isTitleProvided() && request.isAuthorProvided()){
            books = database.findByAuthor(request.getAuthor());
        }
        if (request.isTitleProvided() && request.isAuthorProvided()){
            books = database.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        }

        return new SearchBooksResponse(books, null);
    }
}
