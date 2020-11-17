package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindAllCustomersBySurnameRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindAllCustomersBySurnameValidator {

    public List<CoreError> validate(FindAllCustomersBySurnameRequest findAllCustomersBySurnameRequest){
        List<CoreError> errors = new ArrayList<>();

        if (findAllCustomersBySurnameRequest.getSurname() == null ||
                findAllCustomersBySurnameRequest.getSurname() != findAllCustomersBySurnameRequest.getSurname()){
            errors.add(new CoreError("surname", "Not valid input for surname"));
        }
        return errors;
    }
}
