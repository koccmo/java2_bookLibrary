package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class FindCustomerByNameResponse extends CoreResponse {

    List<Customer> customerList;

    public FindCustomerByNameResponse(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public FindCustomerByNameResponse(List<CoreError> errors, List<Customer> customerList) {
        super(errors);
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList(){
        return customerList;
    }
}
