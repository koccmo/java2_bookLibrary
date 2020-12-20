package team_VK.application.core.services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.BookSearchRequest;
import team_VK.application.core.responses.BookSearchResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.Database;

import java.util.List;
import java.util.stream.Collectors;

public class BookSearchService {

    private final Database database;
    private final BookSearchServiceValidator validator;

    public BookSearchService(Database database, BookSearchServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public BookSearchResponse bookSearch(BookSearchRequest request) {

        List<CoreError> errors;
        errors = validator.validate(request);

        if (errors.size() == 0) {

            switch (request.getSearchCriteria()) {
                case 1:
                    database.getListBooks().stream()
                            .filter(book -> book.getBookTitle() == request.getCriteriaValue())
                            .forEach(book -> System.out.println(book.toString()));
                    break;
                case 2:
                    database.getListBooks().stream()
                            .filter(book -> book.getBookAuthor() == request.getCriteriaValue())
                            .forEach(book -> System.out.println(book.toString()));
                    break;
                case 3:
                    database.getListBooks().stream()
                            .filter(book -> book.getID() == Long.valueOf(request.getCriteriaValue()))
                            .forEach(book -> System.out.println(book.toString()));
                    break;
            }


        }


        return new BookSearchResponse(errors);
    }
}


