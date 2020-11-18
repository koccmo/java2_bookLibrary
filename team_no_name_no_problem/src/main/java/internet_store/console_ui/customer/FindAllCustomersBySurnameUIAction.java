package internet_store.console_ui.customer;


import internet_store.console_ui.UIAction;

import internet_store.core.requests.customer.FindAllCustomersBySurnameRequest;
import internet_store.core.response.customer.FindAllCustomersBySurnameResponse;
import internet_store.core.services.customer.FindAllCustomersBySurnameService;

import java.util.Scanner;

public class FindAllCustomersBySurnameUIAction implements UIAction {

    private FindAllCustomersBySurnameService findAllCustomersBySurname;

    public FindAllCustomersBySurnameUIAction(FindAllCustomersBySurnameService findAllCustomersBySurname) {
        this.findAllCustomersBySurname = findAllCustomersBySurname;
    }

    public void execute(){
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter customer's surname for search: ");
        String surname = in.nextLine();

        FindAllCustomersBySurnameRequest findAllCustomersBySurnameRequest = new FindAllCustomersBySurnameRequest(surname);
        FindAllCustomersBySurnameResponse findAllCustomersBySurnameResponse = findAllCustomersBySurname
                .execute(findAllCustomersBySurnameRequest);

        if (findAllCustomersBySurnameResponse.hasErrors()){
            findAllCustomersBySurnameResponse.getErrors().forEach(System.out::println);
        } else {
            findAllCustomersBySurnameResponse.getCustomerList().forEach(System.out::println);
        }
    }
}
