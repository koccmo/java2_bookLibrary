package internet_store.application.core.responses.customer;

import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class FindByCustomerFirstNameResponse extends CoreResponse {

    private List<Customer> customersFoundByFirstName;

    public FindByCustomerFirstNameResponse(List<CoreError> errors, List<Customer> customersFoundByFirstName) {
        super(errors);
        this.customersFoundByFirstName = customersFoundByFirstName;
    }

    public List<Customer> getCustomersFoundByFirstName() {
        return customersFoundByFirstName;
    }

}
