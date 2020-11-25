package team_VK.application.core.services.admin_services;

import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.domain.Book;
import team_VK.application.database.database_Admin.Database;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AddBookService {

    private final Database database;
    public AddBookServiceValidator validator;


    public AddBookService(Database database, AddBookServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddBookResponse addBook(AddBookRequest request) {

        List<CoreError> errors = validator.validate(request);


        if (errors.size() == 0) {
            Book book = new Book(request.bookTitle, request.bookAuthor, request.bookingDurationPermitted);
            database.addBook(book);
        }
        return new AddBookResponse(errors);

    }

}
