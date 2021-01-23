package lesson_10.core.services.validators;

import lesson_10.core.requests.DeleteBookByAuthorRequest;
import lesson_10.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
