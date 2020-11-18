package internet_store.core.services.customer;

import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerRequestValidator {

    public List<CoreError> validate (AddCustomerRequest addCustomerRequest){
        List<CoreError> errors = new ArrayList<>();

        if (addCustomerRequest.getCustomer().getName() == null || addCustomerRequest.getCustomer().getName().isEmpty()){
            errors.add(new CoreError("name","Not valid input for name"));
        }
        if (addCustomerRequest.getCustomer().getSurname() == null ||
                addCustomerRequest.getCustomer().getSurname().isEmpty()){
            errors.add(new CoreError("surname", "Not valid input for surname"));
        }
        if (addCustomerRequest.getCustomer().getPhoneNumber() == null ||
                addCustomerRequest.getCustomer().getPhoneNumber().isEmpty()){
            errors.add(new CoreError("phone number", "Not valid input for phone number"));
        }
        if (addCustomerRequest.getCustomer().getAddress() == null ||
                addCustomerRequest.getCustomer().getAddress().isEmpty()){
            errors.add(new CoreError("address","Not valid input for address"));
        }
        if (addCustomerRequest.getCustomer().getEmail() == null ||
                addCustomerRequest.getCustomer().getEmail().isEmpty()){
            errors.add(new CoreError("e-mail","Not valid input for e-mail"));
        }
        return errors;
    }
}
