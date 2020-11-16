package internet_store.console_ui.customer;


import internet_store.console_ui.UIAction;

import internet_store.core.requests.customer.FindCustomerBySurnameRequest;
import internet_store.core.response.customer.FindCustomerBySurnameResponse;
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

        FindCustomerBySurnameRequest findCustomerBySurnameRequest = new FindCustomerBySurnameRequest(surname);
        FindCustomerBySurnameResponse findCustomerBySurnameResponse = findAllCustomersBySurname
                .execute(findCustomerBySurnameRequest);

        if (findCustomerBySurnameResponse.hasErrors()){
            findCustomerBySurnameResponse.getErrors().forEach(System.out::println);
        } else {
            findCustomerBySurnameResponse.getCustomerList().forEach(System.out::println);
        }
    }
}
