package lesson_4.core.services.validators;

import lesson_4.core.requests.DeleteBookByTitleRequest;
import lesson_4.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

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
