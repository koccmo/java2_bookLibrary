package internet_store.core.services.customer;

import internet_store.database.customer.CustomerDatabase;
import internet_store.core.domain.Customer;

public class AddCustomerService {

    private final CustomerDatabase customerDatabase;

    public AddCustomerService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public void execute(Customer customer){
        customerDatabase.addCustomer(customer);
    }
}
