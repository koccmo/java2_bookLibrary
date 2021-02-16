package lesson_12.core.services.reader.validators;

import lesson_12.core.requests.reader.DeleteReaderByIdRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteReaderByIdValidator {
    public List<CoreError> validate(DeleteReaderByIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (isReaderIdEmpty(request)) {
            errors.add(new CoreError("Reader ID", "not be empty!"));
        } else {
            if (isReaderIdNegative(request)) {
                errors.add(new CoreError("Reader ID", "not be negative!"));
            }
        }
        return errors;
    }

    private boolean isReaderIdNegative(DeleteReaderByIdRequest request) {
        return request.getReaderIdToDelete() < 0;
    }
    private boolean isReaderIdEmpty(DeleteReaderByIdRequest request) {
        return request.getReaderIdToDelete() == null;
    }

}
