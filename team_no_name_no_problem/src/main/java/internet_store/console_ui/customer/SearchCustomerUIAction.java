package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.customer.SearchCustomerResponse;
import internet_store.core.services.customer.SearchCustomerService;

import java.util.Scanner;

public class SearchCustomerUIAction implements UIAction {

    private final SearchCustomerService searchCustomerService;

    public SearchCustomerUIAction(SearchCustomerService searchCustomerService) {
        this.searchCustomerService = searchCustomerService;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter name to search by name");
        String name = in.nextLine();

        System.out.println("Please enter surname to search by surname");
        String surname = in.nextLine();

        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest(name, surname);
        SearchCustomerResponse searchCustomerResponse = searchCustomerService.execute(searchCustomerRequest);

        if (searchCustomerResponse.hasErrors()){
            searchCustomerResponse.getErrors().forEach(System.out::println);
        } else {
            searchCustomerResponse.getCustomers().forEach(System.out::println);
        }
    }
}
