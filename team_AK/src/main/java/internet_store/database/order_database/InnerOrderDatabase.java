package internet_store.database.order_database;

import internet_store.core.domain.Order;

public interface InnerOrderDatabase {
    void addOrder(Order order);

    void deleteOrder(Order order);

    void showReport();

    Order findById(long id);

    Order findByOrderNumber(int orderNumber);

    boolean isIdExist(long id);

    boolean isEmpty();
}