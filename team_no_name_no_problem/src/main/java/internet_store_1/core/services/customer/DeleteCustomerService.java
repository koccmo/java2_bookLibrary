package internet_store_1.core.services.customer;

import internet_store_1.core.domain.Customer;
import internet_store_1.core.requests.customer.DeleteCustomerRequest;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.customer.DeleteCustomerResponse;
import internet_store_1.database.customer.CustomerDatabase;

import java.util.List;

public class DeleteCustomerService {

    private final CustomerDatabase customerDatabase;
    private final DeleteCustomerRequestValidator deleteCustomerRequestValidator;


    public DeleteCustomerService(CustomerDatabase customerDatabase, DeleteCustomerRequestValidator deleteCustomerRequestValidator) {
        this.customerDatabase = customerDatabase;
        this.deleteCustomerRequestValidator = deleteCustomerRequestValidator;
    }

    public DeleteCustomerResponse execute (DeleteCustomerRequest deleteCustomerRequest){
        List<CoreError> errors = deleteCustomerRequestValidator.validate(deleteCustomerRequest);

        if (!errors.isEmpty()){
            return new DeleteCustomerResponse(errors);
        }
        for (int i = 0; i < customerDatabase.getCustomers().size(); i++){
            if (getCurrentCustomer(i).getId() == deleteCustomerRequest.getId()){
                customerDatabase.deleteCustomer(deleteCustomerRequest.getId());
                return new DeleteCustomerResponse(deleteCustomerRequest.getId());
            }
        }
        errors.add(new CoreError("database", "database doesn't contain this customer"
        + deleteCustomerRequest.getId()));
        return new DeleteCustomerResponse(errors);
    }

    private Customer getCurrentCustomer (int index){
        return customerDatabase.getCustomers().get(index);
    }
}
