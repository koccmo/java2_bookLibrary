package bookLibrary.core.service;

import bookLibrary.core.domain.Book;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.Ordering;
import bookLibrary.core.request.Paging;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.SearchBooksResponse;
import bookLibrary.core.service.validators.SearchBooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class SearchBooksService {
    @Autowired
    private DataBase dataBase;
    @Autowired private SearchBooksValidator searchBooksValidator;

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    public SearchBooksResponse execute(SearchBooksRequest request) {
        List<CoreError> errors = searchBooksValidator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchBooksResponse(errors, null);
        }

        List<Book> books = search(request);
        books = order(books, request.getOrdering());
        books = pagination(books, request.getPaging());
        return new SearchBooksResponse(null, books);
    }

    private List<Book> search(SearchBooksRequest request) {
        List<Book> search = null;
        if (request.getAuthor().isEmpty() && !request.getTitle().isEmpty()) {
            search = dataBase.findByTitle(request.getTitle());
        }
        if (!request.getAuthor().isEmpty() && request.getTitle().isEmpty()) {
            search = dataBase.findByAuthor(request.getAuthor());
        }
        if (!request.getAuthor().isEmpty() && !request.getTitle().isEmpty()) {
            search = dataBase.findByAuthorAndTitle(request.getAuthor(),
                    request.getTitle());
        }
        return search;
    }

    private List<Book> order(List<Book> bookList, Ordering order) {
        if(orderingEnabled && order != null) {
            Comparator<Book> comparator = order.getOrderBy().equals("Author")
                    ? Comparator.comparing(Book::getAuthor)
                    : Comparator.comparing(Book::getTitle);
            if(order.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return bookList.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return bookList;
        }
    }

    private List<Book> pagination (List<Book> bookList, Paging paging) {
        if (pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return bookList.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else return bookList;
    }

//    private List<Book> order(List<Book> bookList, SearchBooksRequest request) {
//        List<Book> sortedList = null;
//        if (request.getOrdering().getOrderBy().equals("Author")
//                && request.getOrdering().getOrderDirection().equals("ASCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getAuthor))
//                    .collect(Collectors.toList());
//        }
//        if (request.getOrdering().getOrderBy().equals("Author")
//                && request.getOrdering().getOrderDirection().equals("DESCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getAuthor).reversed())
//                    .collect(Collectors.toList());
//        }
//        if (request.getOrdering().getOrderBy().equals("Title")
//                && request.getOrdering().getOrderDirection().equals("ASCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getTitle))
//                    .collect(Collectors.toList());
//        }
//        if (request.getOrdering().getOrderBy().equals("Title")
//                && request.getOrdering().getOrderDirection().equals("DESCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getTitle).reversed())
//                    .collect(Collectors.toList());
//        }
//        return sortedList;
//    }
}
