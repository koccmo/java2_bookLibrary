package internet_store.core.response.customer;

import internet_store.core.domain.Customer;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class FindCustomerBySurnameResponse extends CoreResponse {

    List<Customer> customerList;

    public FindCustomerBySurnameResponse(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public FindCustomerBySurnameResponse(List<CoreError> errors, List<Customer> customerList) {
        super(errors);
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList(){
        return customerList;
    }
}
