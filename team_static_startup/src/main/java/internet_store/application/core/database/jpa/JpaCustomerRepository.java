package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {



}
