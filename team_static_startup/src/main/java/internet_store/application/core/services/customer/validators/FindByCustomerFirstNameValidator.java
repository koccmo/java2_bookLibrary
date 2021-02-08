package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.FindByCustomerFirstNameRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindByCustomerFirstNameValidator {

    public List<CoreError> validate(FindByCustomerFirstNameRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (request.getCustomerFirstName().isEmpty() || request.getCustomerFirstName() == null) {
            errors.add(new CoreError("Customer first name", "must not be empty"));
        }
        return errors;
    }

}
