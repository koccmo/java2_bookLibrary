package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.DeleteByCustomerIdRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteByCustomerIdValidator {

    public List<CoreError> validate (DeleteByCustomerIdRequest request){
        List<CoreError> errors = new ArrayList<>();
        if (request.getCustomerId() == null) {
            errors.add(new CoreError("Customer ID", "Should not be empty."));
        } return errors;
    }
}
