package internet_store.services.customer;

import internet_store.database.customer.CustomerDatabase;
import internet_store.domain.Customer;

public class DeleteCustomerService {

    private final CustomerDatabase customerDatabase;

    public DeleteCustomerService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public boolean execute(long id){
        for (int i = 0; i < customerDatabase.getCustomers().size(); i++){
            if (getCurrentCustomer(i).getId() == id){
                customerDatabase.deleteCustomer(i);
                return true;
            }
        }
        return false;
    }

    private Customer getCurrentCustomer (int index){
        return customerDatabase.getCustomers().get(index);
    }
}
