package book_library.core.services;

import book_library.Book;
import book_library.core.database.Database;
import book_library.core.requests.AddBookRequest;
import book_library.core.responses.AddBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.AddBookValidator;

import java.util.List;

public class AddBookService {

    private Database database;
    private AddBookValidator validator;

    public AddBookService(Database database, AddBookValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddBookResponse execute (AddBookRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new AddBookResponse(errors);
        }
        Book book = new Book(request.getTitle(), request.getAuthor());
        database.save(book);

        return new AddBookResponse(book);
    }
}
