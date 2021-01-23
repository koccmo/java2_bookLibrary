package lesson_10.core.services.validators;

import lesson_10.core.requests.DeleteBookByTitleRequest;
import lesson_10.core.responses.CoreError;
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
