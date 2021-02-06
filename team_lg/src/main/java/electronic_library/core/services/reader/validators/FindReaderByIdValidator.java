package electronic_library.core.services.reader.validators;

import electronic_library.core.requests.reader.FindReaderByIdRequest;
import electronic_library.core.responses.CoreError;
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
