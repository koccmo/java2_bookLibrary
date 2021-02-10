package lesson_12.core.services.book.validators;

import lesson_12.core.requests.book.DeleteBookByTitleRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteBookByTitleValidator {

    public List<CoreError> validate(DeleteBookByTitleRequest request){

        List<CoreError> errors = new ArrayList<>();
        String bookTitle = request.getBookTitle();

        if(bookTitle == null || bookTitle.isEmpty()){
            errors.add(new CoreError("bookTitle", "Book title should not be empty!"));
        }
        return errors;
    }
}
