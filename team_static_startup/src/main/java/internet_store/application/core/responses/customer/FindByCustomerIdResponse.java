package internet_store.application.core.responses.customer;

import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindByCustomerIdResponse extends CoreResponse {

    private Optional<Customer> customerFindById;

    public FindByCustomerIdResponse(Optional<Customer> customerFindById) {
        this.customerFindById = customerFindById;
    }

    public FindByCustomerIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Customer> getCustomerFindById() {
        return customerFindById;
    }

    public Customer getCustomerObject() {
        return customerFindById.orElse(null);
    }

}
