package internet_store.application.core.responses.customer;

import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class GetCustomerResponse extends CoreResponse {

    private Customer customer;

    public GetCustomerResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetCustomerResponse(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
