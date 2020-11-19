package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class AddCustomerResponse extends CoreResponse {

    private Customer customer;

    public AddCustomerResponse(List<CoreError> errors){
        super(errors);
    }

    public AddCustomerResponse(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
