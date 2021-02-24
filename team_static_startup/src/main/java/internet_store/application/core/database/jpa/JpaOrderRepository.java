package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {


}
