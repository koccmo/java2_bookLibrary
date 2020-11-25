package internet_store_1.core.services.customer;

import internet_store_1.core.requests.customer.DeleteCustomerRequest;
import internet_store_1.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteCustomerRequestValidator {

    public List<CoreError> validate (DeleteCustomerRequest deleteCustomerRequest){
        List<CoreError> errors = new ArrayList<>();

        if (deleteCustomerRequest.getId() < 1){
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }
}
