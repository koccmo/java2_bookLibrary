package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.FindAllCustomersByNameAndSurnameRequest;
import internet_store.core.response.customer.FindAllCustomersByNameAndSurnameResponse;
import internet_store.core.services.customer.FindAllCustomersByNameAndSurnameService;

import java.util.List;
import java.util.Scanner;

public class FindCustomersByNameAndSurnameUIAction implements UIAction {

    private FindAllCustomersByNameAndSurnameService findAllCustomersByNameAndSurname;

    public FindCustomersByNameAndSurnameUIAction(FindAllCustomersByNameAndSurnameService findAllCustomersByNameAndSurname) {
        this.findAllCustomersByNameAndSurname = findAllCustomersByNameAndSurname;
    }

    public void execute(){
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter customer's name for search: ");
        String name = in.nextLine();

        System.out.println("Please enter customer's surname for search: ");
        String surname = in.nextLine();

        FindAllCustomersByNameAndSurnameRequest findCustomerByNameAndSurnameRequest =
                new FindAllCustomersByNameAndSurnameRequest(name, surname);

        FindAllCustomersByNameAndSurnameResponse findCustomerByNameAndSurnameResponse =
                findAllCustomersByNameAndSurname.execute(findCustomerByNameAndSurnameRequest);

        if (findCustomerByNameAndSurnameResponse.hasErrors()){
            findCustomerByNameAndSurnameResponse.getErrors().forEach(System.out::println);
        }else{
            findCustomerByNameAndSurnameResponse.getCustomerList().forEach(System.out::println);
        }
    }
}
