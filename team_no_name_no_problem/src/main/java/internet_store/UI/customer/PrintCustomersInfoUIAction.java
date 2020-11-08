package internet_store.UI.customer;

import internet_store.UI.UIAction;
import internet_store.database.customer.CustomerDatabase;

public class PrintCustomersInfoUIAction implements UIAction {

    private CustomerDatabase customerDatabase;

    public PrintCustomersInfoUIAction(CustomerDatabase personDatabase){
        this.customerDatabase = personDatabase;
    }

    public void execute(){
        customerDatabase.printCustomersInfo();
    }
}
