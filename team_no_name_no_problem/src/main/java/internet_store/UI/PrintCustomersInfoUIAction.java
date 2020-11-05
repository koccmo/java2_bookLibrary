package internet_store.UI;

import internet_store.database.CustomerDatabase;

public class PrintCustomersInfoUIAction implements UIAction{

    private CustomerDatabase customerDatabase;

    public PrintCustomersInfoUIAction(CustomerDatabase personDatabase){
        this.customerDatabase = personDatabase;
    }

    public void execute(){
        customerDatabase.printCustomersInfo();
    }
}
