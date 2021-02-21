package adventure_time.core.services.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.database.customers.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayCustomerListService {

    @Autowired
    private CustomerRepository database;

    public List<Customers> getActiveCustomersList () {
        return database.findAllActiveCustomers();
    }

    public List<Customers> getInactiveCustomersList () {
        return database.findAllInactiveCustomers();
    }

}
