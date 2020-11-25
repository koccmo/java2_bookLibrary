package internet_store_1.core.services.customer;

import internet_store_1.core.requests.customer.FindCustomerByIdRequest;
import internet_store_1.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindCustomerByIdRequestValidator {

    public List<CoreError> validate(FindCustomerByIdRequest findCustomerByIdRequest){
        List<CoreError> errors = new ArrayList<>();

        if (findCustomerByIdRequest.getId() < 1){
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }
}
