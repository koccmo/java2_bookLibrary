package book_library.core.services;

import book_library.core.domain.Book;
import book_library.core.database.Database;
import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.SearchBooksRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.SearchBooksResponse;
import book_library.core.validators.SearchBooksRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchBooksService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private Database database;
    @Autowired private SearchBooksRequestValidator validator;

    public SearchBooksResponse execute(SearchBooksRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchBooksResponse(null, errors);
        }

        List<Book> books = search(request);
        books = order(books, request.getOrdering());
        books = paging(books, request.getPaging());

        return new SearchBooksResponse(books, null);
    }

    private List<Book> order(List<Book> books, Ordering ordering) {
        if (orderingEnabled && SearchBooksRequestValidator.isOrderingRequested(ordering)) {
            Comparator<Book> comparator = ordering.getOrderBy().equals("title")
                    ? Comparator.comparing(Book::getTitle)
                    : Comparator.comparing(Book::getAuthor);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return books.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return books;
        }
    }

    private List<Book> search(SearchBooksRequest request) {
        List<Book> books = new ArrayList<>();
        if (request.isTitleProvided() && !request.isAuthorProvided()) {
            books = database.findByTitle(request.getTitle());
        }
        if (!request.isTitleProvided() && request.isAuthorProvided()) {
            books = database.findByAuthor(request.getAuthor());
        }
        if (request.isTitleProvided() && request.isAuthorProvided()) {
            books = database.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        }
        return books;
    }

    private List<Book> paging(List<Book> books, Paging paging) {
        if (pagingEnabled && SearchBooksRequestValidator.isPagingRequested(paging)) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return books.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return books;
        }

    }
}
