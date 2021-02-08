package adventure_time.database.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface DatabaseCustomers {

    boolean add(Customers customer);

    boolean activate (Long id);

    boolean deactivate (Long id);

    Optional<Customers> findById (Long id);

    Optional<Customers> findByEmail (String customerEmail);

    boolean updateCustomer (Customers customer, Long id);

    List<Customers> findAllActiveCustomers ();

    List<Customers> findAllInactiveCustomers ();

}
