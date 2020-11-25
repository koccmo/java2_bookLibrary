package internet_store_1.core.response.customer;

import internet_store_1.core.domain.Customer;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindCustomerByIdResponse extends CoreResponse {

    private Optional<Customer> expectedCustomer;

    public FindCustomerByIdResponse(Optional<Customer> customer){
        this.expectedCustomer = customer;
    }

    public FindCustomerByIdResponse(List<CoreError> errors){
        super(errors);
    }

    public Optional<Customer> getCustomer(){
        return expectedCustomer;
    }
}
