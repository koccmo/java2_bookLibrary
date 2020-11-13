package internet_store.UI.customer;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.core.services.customer.DeleteCustomerService;

public class DeleteCustomerUIAction implements UIAction {

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private DeleteCustomerService deleteCustomerService;

    public DeleteCustomerUIAction(DeleteCustomerService deleteCustomerService){
        this.deleteCustomerService = deleteCustomerService;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter customer's id to delete");

        if (deleteCustomerService.execute(id)){
            System.out.println("Customer is deleted");
        } else {
            System.out.println("There's no such id " + id + " in database");
        }
    }
}
