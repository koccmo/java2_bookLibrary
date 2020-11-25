package internet_store_1.core.response.customer;

import internet_store_1.core.domain.Customer;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

import java.util.List;

public class GetAllCustomersResponse extends CoreResponse {

    private List<Customer> customers;

    public GetAllCustomersResponse(List<Customer> customers) {
        this.customers = customers;
    }

    public GetAllCustomersResponse(List<CoreError> errors, List<Customer> customers) {
        super(errors);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
