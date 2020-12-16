package team_VK.application.core.services;

import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;

import java.util.List;
@DIComponent
public class RemoveBookService {

    @DIDependency private Database database;
    @DIDependency public RemoveBookServiceValidator validator;

//    public RemoveBookService(Database database, RemoveBookServiceValidator validator) {
//        this.database = database;
//        this.validator = validator;
//    }

    public RemoveBookResponse removeBook(RemoveBookRequest request) {

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