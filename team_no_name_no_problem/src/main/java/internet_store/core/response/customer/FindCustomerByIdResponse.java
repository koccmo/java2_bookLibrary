package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindCustomerByIdResponse extends CoreResponse {

    private Customer customers;

    public FindCustomerByIdResponse(Customer customer){
        this.customers = customer;
    }

    public FindCustomerByIdResponse(List<CoreError> errors){
        super(errors);
    }

    public Customer getCustomers(){
        return customers;
    }
}
