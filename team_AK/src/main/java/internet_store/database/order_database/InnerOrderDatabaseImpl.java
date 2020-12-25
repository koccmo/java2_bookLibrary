package internet_store.database.order_database;

import internet_store.core.domain.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InnerOrderDatabaseImpl implements InnerOrderDatabase {
    private final List<Order> orders = new ArrayList<>();

    private Long id = 1L;
    private int orderNumber = 100;
    private final Order EMPTY_OBJECT = null;

    @Override
    public void addOrder(Order order) {
        order.setId(id);
        order.setOrderNumber(orderNumber);
        orders.add(order);
        id++;
        orderNumber++;
    }

    @Override
    public void deleteOrder(Order order) {
        orders.remove(order);
    }

    @Override
    public void showReport() {
        orders.forEach(or -> {
            System.out.println("ID: " + or.getId() + " " +
                    "Order Number: " + or.getOrderNumber() + " " + "Order Date: " + or.getOrderDate() + " " +
                    "Total sum: " + or.getTotalSum() + " " + "Ordering status: "
                    + or.getOrderStatus().toString());
            or.getProductsInCart().forEach(pr -> System.out.println("ID: " + pr.getId() + " " +
                    "Title: " + pr.getTitle() + " " + "Description: " + pr.getDescription() + " " +
                    "Quantity: " + pr.getQuantity() + " " + "Price: " + pr.getPrice() + " " + "Sum: " +
                    pr.getSum()));
        });
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Order findById(long id) {
        return orders.stream().filter(pr -> pr.getId() == id).findFirst().orElse(EMPTY_OBJECT);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Order findByOrderNumber(int orderNumber) {
        return orders.stream().filter(pr -> pr.getOrderNumber() == orderNumber)
                .findFirst().orElse(EMPTY_OBJECT);
    }

    @Override
    public boolean isIdExist(long id) {
        return orders.stream().anyMatch(pr -> pr.getId() == id);
    }

    @Override
    public boolean isEmpty() {
        return orders.size() == 0;
    }

    @Override
    public List<Order> getOrder() {
        return orders;
    }
}