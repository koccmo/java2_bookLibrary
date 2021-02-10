package lesson_12.core.services.reader.validators;

import lesson_12.core.requests.reader.FindReaderByIdRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindReaderByIdValidator {
    public List<CoreError> validate(FindReaderByIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getReaderId() == null || (request.getReaderId().isBlank()))  {
            errors.add(new CoreError("Reader ID", "not be empty."));
        } else try {
            Long.parseLong(request.getReaderId());
        } catch (NumberFormatException e) {
            errors.add(new CoreError("Reader ID", "Should be valid."));
        }
        return errors;
    }
}
