package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.RemoveCustomerRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoveCustomerRequestValidator {

    public List<CoreError> validate (RemoveCustomerRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getCustomerId() == null) {
            CoreError error = new CoreError("customerId", "Must be not null");
            errors.add(error);
        }

        if (request.getCustomerId() <= 0) {
            CoreError error = new CoreError("customerId", "Must be bigger than zero");
            errors.add(error);
        }

        return errors;
    }

}
