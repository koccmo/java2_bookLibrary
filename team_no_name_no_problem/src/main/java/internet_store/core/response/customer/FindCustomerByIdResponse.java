package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindCustomerByIdResponse extends CoreResponse {

    private Optional<Customer> customer;

    public FindCustomerByIdResponse(Optional<Customer> customer){
        this.customer = customer;
    }

    public FindCustomerByIdResponse(List<CoreError> errors){
        super(errors);
    }

    public Optional<Customer> getCustomer(){
        return customer;
    }
}
