package team_VK.application.core.services.main_menu_services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.validators.RemoveBookServiceValidator;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;

import java.util.List;
import java.util.Optional;

@DIComponent
public class RemoveBookService {

    @DIDependency
    private Database database;
    @DIDependency public RemoveBookServiceValidator validator;

    public RemoveBookResponse removeBook(RemoveBookRequest request) {

        List<CoreError> errors;
        errors = validator.validate(request, database);

        if (errors.size() == 0) {

            Optional<Book> removableBook = findTheBook(request);
            removableBook.ifPresent(book -> database.deleteBook(book));

        }
        return new RemoveBookResponse(errors);
    }

    private Optional<Book> findTheBook(RemoveBookRequest request) {
        return database.getListBooks().stream()
                        .filter(book -> book.getID() == request.getBookId())
                        .findFirst();
    }
}