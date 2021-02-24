package internet_store.database.jpa;

import internet_store.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrders();

    void addOrder(Order order);
}
