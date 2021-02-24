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

        List<Customers> customers = service.getCustomersList();

        System.out.println("Here is a list of the customers: ");
        if (customers.isEmpty()) {
            System.out.println("The database does not contain any customer.");
        } else {
            customers.forEach(System.out::println);
        }
    }
}
