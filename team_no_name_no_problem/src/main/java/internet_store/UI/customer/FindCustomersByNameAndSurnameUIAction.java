package internet_store.UI.customer;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.database.customer.CustomerDatabase;

public class FindCustomersByNameAndSurnameUIAction implements UIAction {

    private CustomerDatabase customerDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindCustomersByNameAndSurnameUIAction(CustomerDatabase personDatabase){
        this.customerDatabase = personDatabase;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter customers name for search: ");
        String surname = inputCheckUtility.inputValidString("Please enter customers surname for search: ");
        if (customerDatabase.findCustomersByNameAndSurname(name, surname).isPresent()){
            System.out.println(customerDatabase.findCustomersByNameAndSurname(name,surname).get());
        } else {
            System.out.println("Customer with " + " name and surname isn't presented in database!");
        }
    }
}
