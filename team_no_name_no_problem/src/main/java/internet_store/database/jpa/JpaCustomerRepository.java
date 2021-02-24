package internet_store.database.jpa;

import internet_store.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getCustomers();

    List<Customer> addCustomer(Customer customer);

    @Query("delete from Customers c WHERE c.id=:id")
    void deleteCustomerById(Long id);

    Optional<Customer> findById(Long id);

    Optional<Customer> findCustomerByNameAndSurname(String name, String surname);

    List<Customer> findAllCustomersByName(String name);

    List<Customer> findAllCustomersBySurname(String surname);

    List<Customer> containsCustomer(Customer customer);

    List<Customer> containsId(Long id);
}
