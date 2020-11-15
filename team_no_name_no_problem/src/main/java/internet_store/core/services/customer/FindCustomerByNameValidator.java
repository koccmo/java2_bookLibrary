package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindCustomerByNameRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindCustomerByNameValidator {

    public List<CoreError> validate(FindCustomerByNameRequest findCustomerByNameRequest){
        List<CoreError> errors = new ArrayList<>();

        if (findCustomerByNameRequest.getName() == null ||
                findCustomerByNameRequest.getName() != findCustomerByNameRequest.getName()){
            errors.add(new CoreError("name", "Not valid input for name"));
        }
        return errors;
    }
}
