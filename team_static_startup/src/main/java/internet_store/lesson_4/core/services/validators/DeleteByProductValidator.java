package internet_store.lesson_4.core.services.validators;

import internet_store.lesson_4.core.requests.DeleteByProductRequest;
import internet_store.lesson_4.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteByProductValidator {

    public List<CoreError> validate(DeleteByProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductName(request).ifPresent(errors::add);
        validateProductDescription(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductName(DeleteByProductRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty()
                ? Optional.of(new CoreError("Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateProductDescription(DeleteByProductRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isEmpty()
                ? Optional.of(new CoreError("Description", "must not be empty"))
                : Optional.empty());
    }

}
