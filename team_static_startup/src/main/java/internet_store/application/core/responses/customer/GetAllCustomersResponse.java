package internet_store.application.core.responses.customer;

import internet_store.application.core.domain.Customer;

import java.util.List;

public class GetAllCustomersResponse {

    private List<Customer> customerList;

    public GetAllCustomersResponse(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
