package lesson_5.core.services.validators;

import lesson_5.core.requests.DeleteBookByAuthorRequest;
import lesson_5.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteBookByAuthorValidator {
    public List<CoreError> validate(DeleteBookByAuthorRequest request){

        List<CoreError> errors = new ArrayList<>();
        String bookAuthor = request.getBookAuthor();

        if(bookAuthor == null || bookAuthor.isEmpty()){
            errors.add(new CoreError("bookAuthor", "Book author should not be empty!"));
        }
        return errors;
    }
}
