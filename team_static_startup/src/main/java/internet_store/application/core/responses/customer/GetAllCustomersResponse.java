package internet_store.application.core.responses.customer;

import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class GetAllCustomersResponse extends CoreResponse {

    private List<Customer> customerList;

    public GetAllCustomersResponse(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
