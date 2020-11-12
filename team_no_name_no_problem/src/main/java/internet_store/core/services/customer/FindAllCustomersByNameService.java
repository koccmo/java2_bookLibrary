package internet_store.core.services.customer;

import internet_store.database.customer.CustomerDatabase;
import internet_store.domain.Customer;

import java.util.List;

public class FindAllCustomersByNameService {

    private final CustomerDatabase customerDatabase;

    public FindAllCustomersByNameService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public List<Customer> execute(String name){
        return customerDatabase.findAllCustomersByName(name);
    }

}
