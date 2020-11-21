package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.response.customer.DeleteCustomerResponse;
import internet_store.core.services.customer.DeleteCustomerService;

import java.util.Scanner;

public class DeleteCustomerUIAction implements UIAction {

    private DeleteCustomerService deleteCustomerService;

    public DeleteCustomerUIAction(DeleteCustomerService deleteCustomerService){
        this.deleteCustomerService = deleteCustomerService;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter customer's id you want ot delete");
        long id = in.nextLong();

        DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest(id);
        DeleteCustomerResponse deleteCustomerResponse = deleteCustomerService.execute(deleteCustomerRequest);

        if (deleteCustomerResponse.hasErrors()){
            deleteCustomerResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Customer is deleted");
        }
    }
}
