package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class FindCustomerBySurnameResponse extends CoreResponse {

    private Customer expectedCustomer;

    public FindCustomerBySurnameResponse(Customer expectedCustomer) {
        this.expectedCustomer = expectedCustomer;
    }

    public FindCustomerBySurnameResponse(List<CoreError> errors, Customer expectedCustomer) {
        super(errors);
    }

    public Customer getExpectedCustomer() {
        return expectedCustomer;
    }
}
