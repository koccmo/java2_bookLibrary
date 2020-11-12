package internet_store.core.services.customer;

import internet_store.database.customer.CustomerDatabase;
import internet_store.domain.Customer;

import java.util.Optional;

public class FindAllCustomersByNameAndSurnameService {

    private final CustomerDatabase customerDatabase;

    public FindAllCustomersByNameAndSurnameService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public Optional<Customer> execute(String name, String surname){
        return customerDatabase.findCustomersByNameAndSurname(name,surname);
    }
}
