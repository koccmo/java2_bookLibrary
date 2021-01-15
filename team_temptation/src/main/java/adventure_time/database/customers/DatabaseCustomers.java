package adventure_time.database.customers;

import adventure_time.core.domain.Customers;

import java.util.List;
import java.util.Optional;


public interface DatabaseCustomers {

    boolean add(Customers customer);

    boolean activate (Long id);

    boolean deactivate (Long id);

    List<Customers> getCustomersList();

    Optional<Customers> findById (Long id);

    Optional<Customers> findByName (String customerName);

    boolean updateCustomer (Customers customer);

    List<Customers> findAllActiveCustomers ();

    List<Customers> findAllInactiveCustomers ();

}
