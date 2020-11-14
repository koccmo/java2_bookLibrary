package internet_store.core.response.customer;

import internet_store.domain.Customer;

import java.util.List;

public class AddCustomerResponse extends CoreResponse{

    private Customer newCustomer;

    public AddCustomerResponse(List<CoreError> errors){
        super(errors);
    }


}
