package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.ChangeCustomerFirstNameRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeCustomerFirstNameValidator {

    public List<CoreError> validate(ChangeCustomerFirstNameRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getId() == null) {
            errors.add(new CoreError("Customer Id", "Should not be empty"));
        } else if (request.getCustomerNewName() == null || request.getCustomerNewName().isBlank()) {
            errors.add(new CoreError("Customer Name", "Should not be empty"));
        }

        return errors;
    }

}
