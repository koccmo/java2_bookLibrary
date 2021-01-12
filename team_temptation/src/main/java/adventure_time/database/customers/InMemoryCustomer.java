package adventure_time.database.customers;

import adventure_time.core.domain.Customers;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomer {
    private long idCounter =1L;
    private final List<Customers> customers = new ArrayList<>();

    public boolean add(Customers customers) {

        return true;

    }
    public boolean remove (String customerName) {
        return true;
    }
    public List<Customers> getCustomersList() {
        return customers;
    }

}
