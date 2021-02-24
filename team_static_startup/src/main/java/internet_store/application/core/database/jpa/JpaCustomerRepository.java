package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {

    Long addCustomer(Customer customer);

    boolean deleteByCustomerId(Long id);

    List<Customer> findByFirstName(String customerName);

    Optional<Customer> findByCustomerId(Long id);

    List<Customer> findAll();

    boolean changeFirstName(Long id, String newFirstName);

    // Optional<Customer> getById(Long id);

    Optional<Customer> findById(Long id);

}
