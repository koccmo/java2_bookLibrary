package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindCustomerBySurnameRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindCustomerBySurnameValidator {

    public List<CoreError> validate(FindCustomerBySurnameRequest findCustomerBySurnameRequest){
        List<CoreError> errors = new ArrayList<>();

        if (findCustomerBySurnameRequest.getSurname() == null ||
                findCustomerBySurnameRequest.getSurname() != findCustomerBySurnameRequest.getSurname()){
            errors.add(new CoreError("surname", "Not valid input for surname"));
        }
        return errors;
    }
}
