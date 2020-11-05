package internet_store.UI;

import internet_store.database.CustomerDatabase;
import internet_store.domain.Customer;

import java.util.List;

public class FindAllCustomersByNameUIAction implements UIAction{

    private CustomerDatabase customerDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindAllCustomersByNameUIAction(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter customers name for search: ");

        List<Customer> result = customerDatabase.findAllCustomersByName(name);
        if (result.size() == 0){
            System.out.println("Customer's with name " + name + "isn't presented in database!");
        } else {
            result.forEach(System.out::println);
        }
    }
}
