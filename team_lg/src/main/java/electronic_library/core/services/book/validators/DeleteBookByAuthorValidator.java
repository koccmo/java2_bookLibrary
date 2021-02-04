package electronic_library.core.services.book.validators;

import electronic_library.core.requests.book.DeleteBookByAuthorRequest;
import electronic_library.core.responses.CoreError;
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
