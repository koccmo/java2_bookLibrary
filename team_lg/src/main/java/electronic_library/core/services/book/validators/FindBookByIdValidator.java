package electronic_library.core.services.book.validators;

import electronic_library.core.requests.book.FindBookByIdRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
