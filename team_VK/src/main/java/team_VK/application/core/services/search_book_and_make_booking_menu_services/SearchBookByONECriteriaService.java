package team_VK.application.core.services.search_book_and_make_booking_menu_services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.BookSearchRequest;
import team_VK.application.core.responses.BookSearchResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.additional_functions.ResultBookListPrinter;
import team_VK.application.core.services.validators.BookSearchServiceValidator;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;

import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class SearchBookByONECriteriaService {

    @DIDependency
    private Database database;
    @DIDependency private BookSearchServiceValidator validator;
    @DIDependency public ResultBookListPrinter resultBookListPrinter;

    public BookSearchResponse bookSearch(BookSearchRequest request) {

        List<CoreError> errors;
        errors = validator.validate(request);

        if (errors.size() == 0) {
            List<Book> resultList = switch (request.getSearchCriteria()) {
                case 1 -> database.getListBooks().stream()
                        .filter(book -> book.getBookTitle().equals(request.getCriteriaValue()))
                        .collect(Collectors.toList());
                case 2 -> database.getListBooks().stream()
                        .filter(book -> book.getBookAuthor().equals(request.getCriteriaValue()))
                        .collect(Collectors.toList());
                case 3 -> database.getListBooks().stream()
                        .filter(book -> book.getID() == Long.valueOf(request.getCriteriaValue()))
                        .collect(Collectors.toList());
                default -> database.getListBooks();
            };
            resultBookListPrinter.execute(resultList);
        }
        return new BookSearchResponse(errors);
    }
}


