package internet_store_1.core.response.customer;

import internet_store_1.core.domain.Customer;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

import java.util.List;

public class FindAllCustomersByNameResponse extends CoreResponse {

    List<Customer> customerList;

    public FindAllCustomersByNameResponse(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public FindAllCustomersByNameResponse(List<CoreError> errors, List<Customer> customerList) {
        super(errors);
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList(){
        return customerList;
    }
}
