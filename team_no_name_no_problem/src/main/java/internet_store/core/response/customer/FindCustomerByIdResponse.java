package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class FindCustomerByIdResponse extends CoreResponse {

    private Customer expectedCustomer;

    public FindCustomerByIdResponse(Customer customer){
        this.expectedCustomer = customer;
    }

    public FindCustomerByIdResponse(List<CoreError> errors){
        super(errors);
    }

    public Customer getCustomer(){
        return expectedCustomer;
    }
}
