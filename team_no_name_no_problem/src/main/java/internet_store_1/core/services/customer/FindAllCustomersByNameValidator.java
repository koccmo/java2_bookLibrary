package internet_store_1.core.services.customer;

import internet_store_1.core.requests.customer.FindAllCustomersByNameRequest;
import internet_store_1.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindAllCustomersByNameValidator {

    public List<CoreError> validate(FindAllCustomersByNameRequest findAllCustomersByNameRequest){
        List<CoreError> errors = new ArrayList<>();

        if (findAllCustomersByNameRequest.getName() == null ||
                findAllCustomersByNameRequest.getName().isEmpty()){
            errors.add(new CoreError("name", "Not valid input for name"));
        }
        return errors;
    }
}
