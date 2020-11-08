package internet_store.UI.customer;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.database.customer.CustomerDatabase;
import internet_store.domain.Customer;
import internet_store.services.customer.AddCustomerService;

public class AddCustomerUIAction implements UIAction {

    private AddCustomerService addPersonService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddCustomerUIAction(AddCustomerService addPersonService){
        this.addPersonService = addPersonService;
    }

    public void execute(){

        String name = inputCheckUtility.inputValidString("Please enter your name: ");

        String surname = inputCheckUtility.inputValidString("Please enter your surname: ");

        String address = inputCheckUtility.inputValidString("Please enter your address: ");

        String email = inputCheckUtility.inputValidString("Please enter your email: ");

        int phoneNumber = inputCheckUtility.inputValidInteger("Please enter your phone number: ");

        Customer newCustomer = new Customer(name, surname, phoneNumber, address, email);

        addPersonService.execute(newCustomer);

    }
}
