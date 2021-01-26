package adventure_time.ui.customers;

import adventure_time.core.requests.customers.AddCustomerRequest;
import adventure_time.core.responses.customer.AddCustomerResponse;
import adventure_time.core.services.customers.AddCustomerService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class AddCustomerUIAction implements UIAction {
    @Autowired
    private AddCustomerService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name (min 5, max 50 characters): ");
        String name = scanner.nextLine();

        System.out.println("Please enter an actual email (max 30 characters): ");
        String email = scanner.nextLine();

        System.out.println("Please enter a phone number (starting with +371): ");
        String phone = scanner.nextLine();

        System.out.println("According to the security requirements the password must contain:");
        System.out.println("  at least one digit [0-9], at least one lowercase Latin character [a-z],");
        System.out.println("  at least one uppercase Latin character [A-Z], at least one special character like ! @ # & ( ).");
        System.out.println("  Password must contain a length of at least 8 characters and a maximum of 20 characters.");
        System.out.println("Please, enter a password: ");
        String passwordOne = scanner.nextLine();

        System.out.println("Please repeat the password: ");
        String passwordTwo = scanner.nextLine();

        AddCustomerRequest request = new AddCustomerRequest(name, email, phone, passwordOne, passwordTwo);

        AddCustomerResponse response = service.addCustomer(request);

        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println(name + ", your account have been added.");
            System.out.println("Your login is: " + email);
        }

    }
}
