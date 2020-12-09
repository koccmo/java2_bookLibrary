package internet_store.database.order;

import internet_store.core.domain.Order;
import internet_store.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class OrderDatabaseImpl implements OrderDatabase{

    private Long id = 1L;
    List<Order> orders = new ArrayList<>();


    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void addOrder(Order order) {
        order.setId(id);
        orders.add(order);
        id++;
    }
}
