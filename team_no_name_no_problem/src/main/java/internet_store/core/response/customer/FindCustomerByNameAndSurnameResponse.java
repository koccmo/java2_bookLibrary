package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class FindCustomerByNameAndSurnameResponse extends CoreResponse {

    private Customer expectedCustomer;

    public FindCustomerByNameAndSurnameResponse(Customer expectedCustomer) {
        this.expectedCustomer = expectedCustomer;
    }

    public FindCustomerByNameAndSurnameResponse(List<CoreError> errors, Customer expectedCustomer) {
        super(errors);
    }

    public Customer getExpectedCustomer() {
        return expectedCustomer;
    }
}
