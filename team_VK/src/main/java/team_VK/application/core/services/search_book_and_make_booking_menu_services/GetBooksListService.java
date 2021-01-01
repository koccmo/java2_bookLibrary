package team_VK.application.core.services.search_book_and_make_booking_menu_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.core.services.additional_functions.ResultBookListPrinter;
import team_VK.application.core.services.validators.GetBooksListServiceValidator;
import team_VK.application.database.Database;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetBooksListService {

    @Autowired
    private Database database;
    @Autowired private GetBooksListServiceValidator validator;
    @Autowired public ResultBookListPrinter resultBookListPrinter;

    public GetBookListResponse getBooksList(GetBookListRequest request) {

        List<CoreError> errors = validator.validate(request);


        if (errors.size() == 0) {
            List<Book> resultList = switch (request.getSortingCriteria()) {
                case 1 -> database.getListBooks().stream()
                        .sorted(Comparator.comparing(Book::getBookTitle))
                        .collect(Collectors.toList());
                case 2 -> database.getListBooks().stream()
                        .sorted(Comparator.comparing(Book::getBookAuthor))
                        .collect(Collectors.toList());
                case 3 -> database.getListBooks().stream()
                        .sorted((book1, book2) -> ((Long) book1.getID()).compareTo(book2.getID()))
                        .collect(Collectors.toList());
                default -> database.getListBooks();
            };

            resultBookListPrinter.execute(resultList);
        }
        return new GetBookListResponse(errors);

    }
}