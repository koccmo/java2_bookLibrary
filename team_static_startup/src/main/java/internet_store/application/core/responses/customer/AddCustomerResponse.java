package internet_store.application.core.responses.customer;


import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class AddCustomerResponse extends CoreResponse {

    private Customer newCustomer;

    public AddCustomerResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddCustomerResponse(Customer newCustomer) {
        this.newCustomer = newCustomer;
    }

    public Customer getNewCustomer() {
        return newCustomer;
    }

}
