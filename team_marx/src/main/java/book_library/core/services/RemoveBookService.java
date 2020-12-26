package book_library.core.services;

import book_library.core.database.Database;
import book_library.core.requests.RemoveBookRequest;
import book_library.core.responses.AddBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.responses.RemoveBookResponse;
import book_library.core.validators.RemoveBookValidator;

import java.util.List;

public class RemoveBookService {

    private Database database;
    private RemoveBookValidator validator;

    public RemoveBookService(Database database, RemoveBookValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public RemoveBookResponse execute (RemoveBookRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new RemoveBookResponse(errors);
        }
        boolean isBookRemoved = database.deleteById(request.getBookIdToRemove());
        return new RemoveBookResponse(isBookRemoved);
    }
}
