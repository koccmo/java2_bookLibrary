package internet_store.core.services.customer;


import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.services.customer.validators.AddCustomerRequestValidator;
import internet_store.database.customer.CustomerDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component public class AddCustomerService {

    @Autowired private CustomerDatabase customerDatabase;
    @Autowired private AddCustomerRequestValidator addCustomerRequestValidator;

    public AddCustomerResponse execute (AddCustomerRequest addCustomerRequest){

        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);
        if (!errors.isEmpty()){
            return new AddCustomerResponse(errors);
        }

        if (customerDatabase.containsCustomer(addCustomerRequest.getCustomer())){
            errors.add(new CoreError("database", "Database contains the same customer"));
            return new AddCustomerResponse(errors);
        }
        customerDatabase.addCustomer(addCustomerRequest.getCustomer());
        return new AddCustomerResponse(addCustomerRequest.getCustomer());
    }
}
