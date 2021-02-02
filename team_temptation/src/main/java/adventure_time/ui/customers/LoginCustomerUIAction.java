package adventure_time.ui.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.responses.customer.LoginCustomerResponse;
import adventure_time.core.services.customers.LoginCustomerService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginCustomerUIAction implements UIAction {

    @Autowired
    private LoginCustomerService loginService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter login (email):");
        String email = scanner.nextLine();
        System.out.println("Please, enter password:");
        String password = scanner.nextLine();

        LoginCustomerRequest requestLogin = new LoginCustomerRequest(email, password);
        LoginCustomerResponse responseLogin = loginService.loginCustomer(requestLogin);

        if (responseLogin.hasError()) {
            System.out.println("Ooops! Something went wrong! ");
            System.out.println("Your request could not be fulfilled for the reasons: ");
            responseLogin.getErrors().forEach(System.out::println);
            System.out.println();
            return;
        }

        Customers customer = (Customers) responseLogin.getObject();

        System.out.println("Successful authentication!");
        System.out.println();

    }
}
