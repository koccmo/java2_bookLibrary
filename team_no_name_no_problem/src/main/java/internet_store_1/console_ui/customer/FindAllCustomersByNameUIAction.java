package internet_store_1.console_ui.customer;

import internet_store_1.console_ui.UIAction;
import internet_store_1.core.requests.customer.FindAllCustomersByNameRequest;
import internet_store_1.core.response.customer.FindAllCustomersByNameResponse;
import internet_store_1.core.services.customer.FindAllCustomersByNameService;

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
