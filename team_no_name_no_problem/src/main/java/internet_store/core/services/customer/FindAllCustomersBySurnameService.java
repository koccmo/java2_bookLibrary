package internet_store.core.services.customer;

import internet_store.database.customer.CustomerDatabase;
import internet_store.core.domain.Customer;

import java.util.List;

public class FindAllCustomersBySurnameService {

    private final CustomerDatabase customerDatabase;

    public FindAllCustomersBySurnameService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public List<Customer> execute(String surname){
        return customerDatabase.findAllCustomersBySurname(surname);
    }
}
