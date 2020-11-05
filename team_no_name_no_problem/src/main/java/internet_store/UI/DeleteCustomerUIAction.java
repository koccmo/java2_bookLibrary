package internet_store.UI;

import internet_store.database.CustomerDatabase;

public class DeleteCustomerUIAction implements UIAction{

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private CustomerDatabase customerDatabase;

    public DeleteCustomerUIAction(CustomerDatabase personDatabase){
        this.customerDatabase = personDatabase;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter customer's id to delete");

        if (customerDatabase.deleteCustomer(id)){
            System.out.println("Customer is deleted");
        } else {
            System.out.println("There's no such id " + id + " in database");
        }
    }
}
