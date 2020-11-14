package internet_store.console_ui.customer;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.domain.Customer;
import internet_store.core.services.customer.FindAllCustomersBySurnameService;

import java.util.List;

public class FindAllCustomersBySurnameUIAction implements UIAction {

    private FindAllCustomersBySurnameService findAllCustomersBySurname;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindAllCustomersBySurnameUIAction(FindAllCustomersBySurnameService findAllCustomersBySurname) {
        this.findAllCustomersBySurname = findAllCustomersBySurname;
    }

    public void execute(){
        String surname = inputCheckUtility.inputValidString("Please enter customer's surname for search: ");

        List<Customer> result = findAllCustomersBySurname.execute(surname);
        if (result.size() == 0){
            System.out.println("Customer's with surname " + surname + " isn't presented in database!");
        } else {
            result.forEach(System.out::println);
        }
    }
}
