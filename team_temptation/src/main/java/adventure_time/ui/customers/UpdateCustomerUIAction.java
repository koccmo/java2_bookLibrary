package adventure_time.ui.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.requests.customers.UpdateCustomerRequest;
import adventure_time.core.responses.customer.LoginCustomerResponse;
import adventure_time.core.responses.customer.UpdateCustomerResponse;
import adventure_time.core.services.customers.UpdateCustomerService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class UpdateCustomerUIAction implements UIAction {

    @Autowired
    private UpdateCustomerService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter login (email):");
        String email = scanner.nextLine();
        System.out.println("Please, enter password:");
        String password = scanner.nextLine();

        LoginCustomerRequest requestLogin = new LoginCustomerRequest(email, password);
        LoginCustomerResponse responseLogin = service.loginCustomer(requestLogin);

        if (responseLogin.hasError()) {
            System.out.println("Ooops! Something went wrong! ");
            System.out.println("Your request could not be fulfilled for the reasons: ");
            responseLogin.getErrors().forEach(System.out::println);
            System.out.println();
            return; // TODO instead of return use exception?
        }

        Customers customer = (Customers) responseLogin.getObject();

        System.out.println("Successful authentication!");
        System.out.println();
        String name;
        System.out.println("Would you like to change name (y/n)?");
        if (scanner.nextLine().equals("y")) {
            System.out.println("Enter new name (min 5, max 50 characters): ");
            name = scanner.nextLine();
        } else name = customer.getCustomerName();
        System.out.println("Would you like to change email (y/n)?");
        if (scanner.nextLine().equals("y")) {
            System.out.println("Enter new email (max 30 characters): ");
            email = scanner.nextLine();
        } else email = customer.getCustomerEmail();
        String phone;
        System.out.println("Would you like to change phone (y/n)?");
        if (scanner.nextLine().equals("y")) {
            System.out.println("Enter new phone (starting with +371): ");
            phone = scanner.nextLine();
        } else phone = customer.getCustomerPhone();
        String passwordOne;
        String passwordTwo;
        System.out.println("Would you like to change password (y/n)?");
        if (scanner.nextLine().equals("y")) {
            System.out.println("According to the security requirements the password must contain:");
            System.out.println("  at least one digit [0-9], at least one lowercase Latin character [a-z],");
            System.out.println("  at least one uppercase Latin character [A-Z], at least one special character like ! @ # & ( ).");
            System.out.println("  Password must contain a length of at least 8 characters and a maximum of 20 characters.");
            System.out.println("Enter new password: ");
            passwordOne = scanner.nextLine();
            System.out.println("Please repeat the password: ");
            passwordTwo = scanner.nextLine();
        } else {
            passwordOne = customer.getCustomerPassword();
            passwordTwo = passwordOne;
        }

        UpdateCustomerRequest requestUpdate = new UpdateCustomerRequest(name, email, phone, passwordOne, passwordTwo, customer.getCustomerID());
        UpdateCustomerResponse responseUpdate = service.updateCustomer(requestUpdate);

        if (responseUpdate.hasError()) {
            System.out.println("Ooops! Something went wrong! ");
            System.out.println("Your request could not be fulfilled for the reasons: ");
            responseUpdate.getErrors().forEach(System.out::println);
            System.out.println();
            return;
        }

        System.out.println("Your data is updated");
    }
}
