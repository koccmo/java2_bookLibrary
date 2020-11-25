package internet_store_1.core.response.customer;

import internet_store_1.core.domain.Customer;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

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
