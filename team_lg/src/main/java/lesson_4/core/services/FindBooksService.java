package lesson_4.core.services;

import lesson_4.core.database.ElectronicLibrary;
import lesson_4.core.domain.Book;
import lesson_4.core.requests.FindBooksRequest;
import lesson_4.core.responses.CoreError;
import lesson_4.core.responses.FindBooksResponse;
import lesson_4.core.services.validators.FindBooksRequestValidator;
import lesson_4.core.services.validators.Ordering;
import lesson_4.core.services.validators.Paging;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindBooksService {
    private ElectronicLibrary electronicLibrary;
    private FindBooksRequestValidator validator;

    public FindBooksService(ElectronicLibrary electronicLibrary,
                            FindBooksRequestValidator validator) {
        this.electronicLibrary = electronicLibrary;
        this.validator = validator;
    }

      public FindBooksResponse execute(FindBooksRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindBooksResponse(null, errors);
        }

        List<Book> books = find(request);
        books = order(books, request.getOrdering());
        books = paging(books, request.getPaging());

        return new FindBooksResponse(books, null);
    }

    private List<Book> order(List<Book> books, Ordering ordering) {
        if (ordering != null) {
            Comparator<Book> comparator = ordering.getOrderBy().equals("title")
                    ? Comparator.comparing(Book::getBookTitle)
                    : Comparator.comparing(Book::getBookAuthor);
            if (ordering.getOrderDirection().equals("DESC")) {
                comparator = comparator.reversed();
            }
            return books.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return books;
        }
    }

    private List<Book> find(FindBooksRequest request) {
        List<Book> books = new ArrayList<>();
        if (request.isBookTitleProvided() && !request.isBookAuthorProvided()) {
            books = electronicLibrary.findBookByTitle(request.getBookTitle());
        }
        if (!request.isBookTitleProvided() && request.isBookAuthorProvided()) {
            books = electronicLibrary.findBookByAuthor(request.getBookAuthor());
        }
        if (request.isBookTitleProvided() && request.isBookAuthorProvided()) {
            books = electronicLibrary.findByTitleAndAuthor(request.getBookTitle(), request.getBookAuthor());
        }
        return books;
    }

    private List<Book> paging(List<Book> books, Paging paging) {
        if (paging != null) {
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
