package internet_store.core.services.customer;


import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.database.customer.CustomerDatabase;

import java.util.List;

public class AddCustomerService {

    private final CustomerDatabase customerDatabase;
    private final AddCustomerRequestValidator addCustomerRequestValidator;


    public AddCustomerService(CustomerDatabase customerDatabase, AddCustomerRequestValidator addCustomerRequestValidator) {
        this.customerDatabase = customerDatabase;
        this.addCustomerRequestValidator = addCustomerRequestValidator;
    }

    public AddCustomerResponse execute (AddCustomerRequest addCustomerRequest){

        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);
        if (!errors.isEmpty()){
            return new AddCustomerResponse(errors);
        }
        customerDatabase.addCustomer(addCustomerRequest.getCustomer());
        return new AddCustomerResponse(addCustomerRequest.getCustomer());
    }
}
