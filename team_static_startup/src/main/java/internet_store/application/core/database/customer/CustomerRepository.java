package internet_store.application.core.database.customer;

import internet_store.application.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Long add(Customer customer);

    boolean deleteById(Long id);

    List<Customer> findByFirstName(String customerName);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

}
