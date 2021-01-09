package lesson_5.core.services.validators;

import lesson_5.core.requests.FindBookByIdRequest;
import lesson_5.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindBookByIdValidator {
    public List<CoreError> validate(FindBookByIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getBookId() == null || (request.getBookId().isBlank()))  {
            errors.add(new CoreError("Book ID", "not be empty."));
        } else try {
            Long.parseLong(request.getBookId());
        } catch (NumberFormatException e) {
            errors.add(new CoreError("Book ID", "Should be valid."));
        }
        return errors;
    }
}
