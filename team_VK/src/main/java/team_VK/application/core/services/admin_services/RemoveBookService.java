package team_VK.application.core.services.admin_services;

import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.database.database_Admin.Database;

import java.util.List;

public class RemoveBookService {

    private final Database database;

    public RemoveBookService(Database database) {
        this.database = database;
    }

    public RemoveBookResponse removeBook(RemoveBookRequest request) {

        RemoveBookServiceValidator validator = new RemoveBookServiceValidator();

        List<CoreError> errors;
        errors = validator.validate(request, database);

        if (errors.size() == 0) {
            database.getListBooks().stream()
                    .filter(book -> book.getID() == request.getBookId())
                    .findFirst()
                    .ifPresent(book -> database.getListBooks().remove(book));

        }
        return new RemoveBookResponse(errors);
    }
}