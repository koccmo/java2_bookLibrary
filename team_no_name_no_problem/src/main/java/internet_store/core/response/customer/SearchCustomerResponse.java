package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class SearchCustomerResponse extends CoreResponse {

    private List<Customer> customer;

    public SearchCustomerResponse(List<Customer> customer) {
        this.customer = customer;
    }

    public SearchCustomerResponse(List<CoreError> errors, List<Customer> customer) {
        super(errors);
        this.customer = customer;
    }

    public List<Customer> getCustomers(){
        return customer;
    }
}
