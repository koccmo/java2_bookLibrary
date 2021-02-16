package internet_store.application.core.responses.customer;

import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class UpdateCustomerResponse extends CoreResponse {

    private Customer updatedCustomer;

    public UpdateCustomerResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateCustomerResponse(Customer updatedCustomer) {
        this.updatedCustomer = updatedCustomer;
    }

    public Customer getUpdatedCustomer() {
        return updatedCustomer;
    }

}
