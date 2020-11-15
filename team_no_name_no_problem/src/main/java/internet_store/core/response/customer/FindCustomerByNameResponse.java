package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class FindCustomerByNameResponse extends CoreResponse {

    private Customer expectedCustomer;

    public FindCustomerByNameResponse(Customer expectedCustomer) {
        this.expectedCustomer = expectedCustomer;
    }

    public FindCustomerByNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public Customer getExpectedCustomer() {
        return expectedCustomer;
    }
}
