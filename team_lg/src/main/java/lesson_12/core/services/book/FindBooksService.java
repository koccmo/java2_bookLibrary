package lesson_12.core.services.book;

import lesson_12.core.database.book.ElectronicLibraryRepository;
import lesson_12.core.domain.Book;
import lesson_12.core.requests.Ordering;
import lesson_12.core.requests.Paging;
import lesson_12.core.requests.book.FindBooksRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.book.FindBooksResponse;
import lesson_12.core.services.book.validators.FindBooksRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class FindBooksService {

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

    @Autowired
    private FindBooksRequestValidator validator;

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
