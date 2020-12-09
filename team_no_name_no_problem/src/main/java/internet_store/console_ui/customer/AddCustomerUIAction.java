package internet_store.console_ui.customer;


import internet_store.console_ui.UIAction;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddCustomerUIAction implements UIAction {

    @DIDependency private AddCustomerService addCustomerService;

    @Override
    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter customer's name: ");
        String name = in.nextLine();

        System.out.println("Please enter customer's surname: ");
        String surname = in.nextLine();

        System.out.println("Please enter customer's phone number: ");
        String phoneNumber = in.nextLine();

        System.out.println("Please enter customer's address: ");
        String address = in.nextLine();

        System.out.println("Please enter customer's e-mail: ");
        String email = in.nextLine();

        Customer newCustomer = new Customer(name, surname, phoneNumber, address, email);

        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(newCustomer);
        AddCustomerResponse addCustomerResponse = addCustomerService.execute(addCustomerRequest);
        if (addCustomerResponse.hasErrors()){
            addCustomerResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Customer was successfully added");
        }
    }
}
