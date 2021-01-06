package book_library.core.services;

import book_library.core.database.Database;
import book_library.core.requests.RemoveBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.RemoveBookResponse;
import book_library.core.validators.RemoveBookRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveBookService {

    @Autowired
    private Database database;
    @Autowired
    private RemoveBookRequestValidator validator;

    public RemoveBookResponse execute(RemoveBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveBookResponse(errors);
        }
        boolean isBookRemoved = database.deleteById(request.getBookIdToRemove());
        return new RemoveBookResponse(isBookRemoved);
    }
}
