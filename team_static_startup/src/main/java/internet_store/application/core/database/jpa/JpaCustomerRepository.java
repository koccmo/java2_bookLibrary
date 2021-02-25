package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {

    boolean deleteByCustomerId(Long id);

    List<Customer> findByFirstName(String customerName);

    boolean changeFirstName(Long id, String newFirstName);

}
