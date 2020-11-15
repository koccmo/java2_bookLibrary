package team_VK.application.services;

import team_VK.application.requests.AddBookRequest;
import team_VK.application.Book;
import team_VK.application.database.Database;
import team_VK.application.responses.AddBookResponse;
import team_VK.application.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AddBookService {

    private final Database database;

    public AddBookService(Database database) {
        this.database = database;
    }

    public AddBookResponse addBook(AddBookRequest request) {

        List<CoreError> errors = new ArrayList<>();
        FieldValidatorUI validator = new FieldValidatorUI();
        errors = validator.bookTitleFieldValidate (request, errors);
        errors = validator.bookAuthorFieldValidate (request, errors);


        if (errors.size() == 0) {
            Book book = new Book(request.bookTitle, request.bookAuthor);
            database.addBook(book);
            return new AddBookResponse();
        } else {
            return new AddBookResponse(errors);
        }
    }

}
