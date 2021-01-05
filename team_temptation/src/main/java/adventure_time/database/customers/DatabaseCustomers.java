package adventure_time.database.customers;

import adventure_time.domain.Customers;

import java.util.List;


public interface DatabaseCustomers {

    boolean add(Customers customer);

    boolean remove(String customerName);

    List<Customers> getCustomersList();
}
