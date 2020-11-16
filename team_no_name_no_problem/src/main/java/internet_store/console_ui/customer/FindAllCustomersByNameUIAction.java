package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.FindCustomerByNameRequest;
import internet_store.core.response.customer.FindCustomerByNameResponse;
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

        FindCustomerByNameRequest findCustomerByNameRequest = new FindCustomerByNameRequest(name);
        FindCustomerByNameResponse findCustomerByNameResponse = findAllCustomersByName
                .execute(findCustomerByNameRequest);

        if (findCustomerByNameResponse.hasErrors()){
            findCustomerByNameResponse.getErrors().forEach(System.out::println);
        } else {
            findCustomerByNameResponse.getCustomerList().forEach(System.out::println);
        }
    }
}
