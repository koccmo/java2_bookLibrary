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
    private DatabaseCustomers database;

    public List<Customers> getActiveCustomersList () {
        return database.findAllActiveCustomers();
    }

    public List<Customers> getInactiveCustomersList () {
        return database.findAllInactiveCustomers();
    }

}
