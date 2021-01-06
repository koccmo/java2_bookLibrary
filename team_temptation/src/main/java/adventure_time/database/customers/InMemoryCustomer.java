package adventure_time.database.customers;

import adventure_time.core.domain.Customers;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomer {
    private long idCounter =1L;
    private final List<Customers> customers = new ArrayList<>();

    public boolean add(Customers customers) {
        if (!this.customers.isEmpty()){
            for (Customers item : this.customers) {
                if (item.getcustomerName().equals(customers.getcustomerName())) return false;

            }
        }
        customers.setcustomerID(idCounter);
        this.customers.add(customers);
        idCounter++;
        return true;

    }
    public boolean remove (String customerName) {
        return getCustomersList().removeIf(items -> items.getcustomerName().equals(customerName));
    }
    public List<Customers> getCustomersList() {
        return customers;
    }

}
