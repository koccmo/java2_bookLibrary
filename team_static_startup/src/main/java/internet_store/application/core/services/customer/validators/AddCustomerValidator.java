package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.AddCustomerRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddCustomerValidator {

    public List<CoreError> validate(AddCustomerRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateCustomerFirstName(request).ifPresent(errors::add);
        validateCustomerSecondName(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateCustomerFirstName(AddCustomerRequest request) {
        return (request.getCustomerFirstName() == null || request.getCustomerFirstName().isBlank()
                ? Optional.of(new CoreError("First Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateCustomerSecondName(AddCustomerRequest request) {
        return (request.getCustomerSecondName() == null || request.getCustomerSecondName().isBlank()
                ? Optional.of(new CoreError("Second Name", "must not be empty"))
                : Optional.empty());
    }

}
