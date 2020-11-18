package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.FindAllCustomersByNameRequest;
import internet_store.core.response.customer.FindAllCustomersByNameResponse;
import internet_store.core.services.customer.FindAllCustomersByNameService;

import java.util.Scanner;

public class FindAllCustomersByNameUIAction implements UIAction {

    private FindAllCustomersByNameService findAllCustomersByName;

    public FindAllCustomersByNameUIAction(FindAllCustomersByNameService findAllCustomersByName){
        this.findAllCustomersByName = findAllCustomersByName;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter customer name for search: ");
        String name = in.nextLine();

        FindAllCustomersByNameRequest findAllCustomersByNameRequest = new FindAllCustomersByNameRequest(name);
        FindAllCustomersByNameResponse findAllCustomersByNameResponse = findAllCustomersByName
                .execute(findAllCustomersByNameRequest);

        if (findAllCustomersByNameResponse.hasErrors()){
            findAllCustomersByNameResponse.getErrors().forEach(System.out::println);
        } else {
            findAllCustomersByNameResponse.getCustomerList().forEach(System.out::println);
        }
    }
}
