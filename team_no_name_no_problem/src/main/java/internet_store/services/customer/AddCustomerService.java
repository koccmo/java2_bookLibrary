package internet_store.services.customer;

import internet_store.database.customer.CustomerDatabase;
import internet_store.domain.Customer;

public class AddCustomerService {

    private final CustomerDatabase customerDatabase;

    public AddCustomerService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public void execute(Customer customer){
        customerDatabase.addCustomer(customer);
    }
}
