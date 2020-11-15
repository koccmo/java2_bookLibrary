package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class GetCustomersResponse extends CoreResponse {

    private List<Customer> customers;

    public GetCustomersResponse(List<Customer> customers) {
        this.customers = customers;
    }

    public GetCustomersResponse(List<CoreError> errors, List<Customer> customers) {
        super(errors);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
