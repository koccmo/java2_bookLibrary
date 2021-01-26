package adventure_time.database.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;

import java.util.List;
import java.util.Optional;


public interface DatabaseCustomers {

    boolean add(Customers customer);

    boolean activate (Long id);

    boolean deactivate (Long id);

    List<Customers> getCustomersList();

    Optional<Customers> findById (Long id);

    Optional<Customers> findByEmail (String customerEmail);

    boolean updateCustomer (Customers customer, Long id);

    List<Customers> findAllActiveCustomers ();

    List<Customers> findAllInactiveCustomers ();

    Long checkLogin (String email, String password);

    Long checkLoginBeforeUpdate (String email, String password);

}
