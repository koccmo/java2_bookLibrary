package internet_store.lesson_6.core.services.validators;

import internet_store.lesson_6.core.requests.FindByIdRequest;
import internet_store.lesson_6.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindByIdValidator {

    public List<CoreError> validate(FindByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductId() == null || (request.getProductId().isBlank())) {
            errors.add(new CoreError("Product ID", "Should not be empty."));
        } else try {
            Long.parseLong(request.getProductId());
        } catch (NumberFormatException e) {
            errors.add(new CoreError("Product ID", "Should be valid."));
        }
        return errors;

    }
}
