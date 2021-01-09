package lesson_5.core.services.validators;

import lesson_5.core.requests.DeleteBookByIdRequest;
import lesson_5.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteBookByIdValidator {

    public List<CoreError> validate(DeleteBookByIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (isBookIdEmpty(request)) {
            errors.add(new CoreError("Book ID", "not be empty!"));
        } else {
            if (isBookIdNegative(request)) {
                errors.add(new CoreError("Book ID", "not be negative!"));
            }
        }
        return errors;
    }

    private boolean isBookIdNegative(DeleteBookByIdRequest request) {
        return request.getBookIdToDelete() < 0;
    }

    private boolean isBookIdEmpty(DeleteBookByIdRequest request) {
        return request.getBookIdToDelete() == null;
    }
}
