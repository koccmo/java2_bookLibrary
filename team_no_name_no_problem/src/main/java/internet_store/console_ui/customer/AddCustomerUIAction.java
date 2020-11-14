package internet_store.console_ui.customer;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.domain.Customer;
import internet_store.core.services.customer.AddCustomerService;

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
