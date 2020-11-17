package internet_store.console_ui.customer;

/*
import internet_store.console_ui.UIAction;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.FindAllCustomersByNameAndSurnameRequest;
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

        System.out.println("Please enter customer's name and surname for search: ");
        String name = in.nextLine();
        String surname = in.nextLine();

        FindAllCustomersByNameAndSurnameRequest findCustomerByNameAndSurnameRequest =
                new FindAllCustomersByNameAndSurnameRequest(name, surname);
        List<Customer> findCustomerByNameAndSurnameResponse =
                findAllCustomersByNameAndSurname.execute(findCustomerByNameAndSurnameRequest.getName(),
                        findCustomerByNameAndSurnameRequest.getSurname());

        if (findCustomerByNameAndSurnameResponse)
    }
}*/
