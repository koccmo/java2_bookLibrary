package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayCustomerListService {

    private DatabaseCustomers databaseCustomers;

    public List<Customers> getCustomersList () {
        return databaseCustomers.getCustomersList();
    }

}
