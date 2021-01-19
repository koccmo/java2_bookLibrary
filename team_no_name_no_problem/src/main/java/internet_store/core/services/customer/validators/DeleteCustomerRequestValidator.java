package internet_store.core.services.customer.validators;

import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component public class DeleteCustomerRequestValidator {

    public List<CoreError> validate (DeleteCustomerRequest deleteCustomerRequest){
        List<CoreError> errors = new ArrayList<>();

        if ((deleteCustomerRequest.getId() < 1) || (deleteCustomerRequest.getId() == null)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }
}
