package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class SearchCustomerResponse extends CoreResponse {

    private List<Customer> customers;

    public SearchCustomerResponse(List<Customer> customers) {
        this.customers = customers;
    }

    public SearchCustomerResponse(List<CoreError> errors, List<Customer> customers) {
        super(errors);
        this.customers = customers;
    }

    public List<Customer> getCustomers(){
        return customers;
    }
}
