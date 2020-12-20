package team_VK.application.core.services;

import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.domain.Book;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.responses.CoreError;

import java.util.List;
@DIComponent
public class AddBookService {

    @DIDependency private Database database;
    @DIDependency public AddBookServiceValidator validator;


    public AddBookResponse addBook(AddBookRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (errors.size() == 0) {
            Book book = new Book(request.bookTitle, request.bookAuthor, request.bookingDurationPermitted);
            database.addBook(book);
        }
        return new AddBookResponse(errors);

    }

}
