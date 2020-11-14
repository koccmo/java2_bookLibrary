package internet_store.console_ui.customer;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.domain.Customer;
import internet_store.core.services.customer.FindAllCustomersByNameService;

import java.util.List;

public class FindAllCustomersByNameUIAction implements UIAction {

    private FindAllCustomersByNameService findAllCustomersByNameService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindAllCustomersByNameUIAction(FindAllCustomersByNameService findAllCustomersByNameService) {
        this.findAllCustomersByNameService = findAllCustomersByNameService;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter customers name for search: ");

        List<Customer> result = findAllCustomersByNameService.execute(name);
        if (result.size() == 0){
            System.out.println("Customer's with name " + name + "isn't presented in database!");
        } else {
            result.forEach(System.out::println);
        }
    }
}
