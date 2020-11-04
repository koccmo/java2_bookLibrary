package internet_store.UI;

import internet_store.CustomerDatabase;
import internet_store.domain.Customer;

import java.util.List;

public class FindAllCustomersBySurnameUIAction implements UIAction{

    private CustomerDatabase customerDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindAllCustomersBySurnameUIAction(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public void execute(){
        String surname = inputCheckUtility.inputValidString("Please enter customer's surname for search: ");

        List<Customer> result = customerDatabase.findAllCustomersBySurname(surname);
        if (result.size() == 0){
            System.out.println("Customer's with surname " + surname + " isn't presented in database!");
        } else {
            result.forEach(System.out::println);
        }
    }
}
