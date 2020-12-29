package book_library.core.services;

import book_library.Book;
import book_library.core.database.Database;
import book_library.core.requests.Ordering;
import book_library.core.requests.SearchBooksRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.SearchBooksResponse;
import book_library.core.validators.SearchBooksRequestValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Book> books = search(request);
        books = order(books, request.getOrdering());

        return new SearchBooksResponse(books, null);
    }

    private List<Book> order (List<Book> books, Ordering ordering) {
        if (ordering != null) {
            Comparator<Book> comparator = ordering.getOrderBy().equals("title")
                    ? Comparator.comparing(Book::getTitle)
                    : Comparator.comparing(Book::getAuthor);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return books.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return  books;
        }
    }

    private List<Book> search (SearchBooksRequest request){
        List<Book> books = new ArrayList<>();
        if (request.isTitleProvided() && !request.isAuthorProvided()){
            books = database.findByTitle(request.getTitle());
        }
        if (!request.isTitleProvided() && request.isAuthorProvided()){
            books = database.findByAuthor(request.getAuthor());
        }
        if (request.isTitleProvided() && request.isAuthorProvided()){
            books = database.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        }
        return books;
    }
}
