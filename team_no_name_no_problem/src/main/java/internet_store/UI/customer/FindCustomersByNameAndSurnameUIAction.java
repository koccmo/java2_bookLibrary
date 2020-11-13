package internet_store.UI.customer;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.core.services.customer.FindAllCustomersByNameAndSurnameService;

public class FindCustomersByNameAndSurnameUIAction implements UIAction {

    private FindAllCustomersByNameAndSurnameService findAllCustomersByNameAndSurnameService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindCustomersByNameAndSurnameUIAction(FindAllCustomersByNameAndSurnameService
                                                         findAllCustomersByNameAndSurnameService){
        this.findAllCustomersByNameAndSurnameService = findAllCustomersByNameAndSurnameService;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter customers name for search: ");
        String surname = inputCheckUtility.inputValidString("Please enter customers surname for search: ");
        if (findAllCustomersByNameAndSurnameService.execute(name, surname).isPresent()){
            System.out.println(findAllCustomersByNameAndSurnameService.execute(name,surname).get());
        } else {
            System.out.println("Customer with " + " name and surname isn't presented in database!");
        }
    }
}
