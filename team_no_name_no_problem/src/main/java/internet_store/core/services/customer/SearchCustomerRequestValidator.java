package internet_store.core.services.customer;

import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchCustomerRequestValidator {

    public List<CoreError> validate(SearchCustomerRequest searchCustomerRequest){

        List<CoreError> errors = new ArrayList<>();

        if (isNameAndSurnameEmpty(searchCustomerRequest.getName(), searchCustomerRequest.getSurname())){
            errors.add(new CoreError("name and surname", "Not valid input for search"));
        }
        return errors;
    }

    private boolean isNameAndSurnameEmpty(String name, String surname){
        return (name == null || name.isEmpty()) && (surname == null || surname.isEmpty());
    }
}
