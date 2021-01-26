package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.database.customers.DatabaseCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DisplayCustomerListService {

    @Autowired
    private DatabaseCustomers databaseCustomers;

    public List<Customers> getActiveCustomersList () {
        return databaseCustomers.findAllActiveCustomers();
    }

    public List<Customers> getInactiveCustomersList () {
        List<Customers> result = new ArrayList<>();
        result = databaseCustomers.findAllInactiveCustomers();
        return result;
    }

}
