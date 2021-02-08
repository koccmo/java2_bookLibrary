package adventure_time.ui.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.services.customers.DisplayCustomerListService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class DisplayCustomerUIAction implements UIAction {

    @Autowired
    private DisplayCustomerListService service;

    @Override
    public void execute() {

        System.out.println("Would you like to get a list of: ");
        System.out.println("   1. Active customers ");
        System.out.println("   2. Inactive customers ");

        Scanner scanner = new Scanner(System.in);

        List<Customers> customers;
        String criteria;
        if (scanner.nextLine().equals("1")) {
            customers = service.getActiveCustomersList();
            criteria = "active";
        } else {
            customers = service.getInactiveCustomersList();
            criteria = "inactive";
        }

        System.out.println("Here is a list of the " + criteria + " customers: ");
        if (customers.isEmpty()) {
            System.out.println("The database does not contain any " + criteria + " customer.");
        } else {
            customers.forEach(System.out::println);
        }
    }
}
