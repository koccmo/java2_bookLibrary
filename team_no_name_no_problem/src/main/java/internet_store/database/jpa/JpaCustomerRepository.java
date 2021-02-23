package internet_store.database.jpa;

import internet_store.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getCustomers();

    List<Customer> addCustomer(Customer customer);

    List<Customer> deleteCustomerById(Long id);

    Optional<Customer> findById(Long id);

    List<Customer> findCustomerByNameAndSurname(String name, String surname);

    List<Customer> findAllCustomersByName(String name);

    List<Customer> findAllCustomersBySurname(String surname);

    List<Customer> containsCustomer(Customer customer);

    List<Customer> containsId(Long id);
}
