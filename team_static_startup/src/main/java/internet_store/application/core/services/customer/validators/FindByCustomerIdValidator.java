package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.FindByCustomerIdRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindByCustomerIdValidator {

    public List<CoreError> validate(FindByCustomerIdRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getCustomerId() == null || request.getCustomerId().isBlank()){
            errors.add(new CoreError("Customer Id", "Should not be empty"));
        } else try {
            Long.valueOf(request.getCustomerId());
        } catch (NumberFormatException e){
            errors.add(new CoreError("Customer Id", "Should be valid."));
        }

        return errors;
    }

}
