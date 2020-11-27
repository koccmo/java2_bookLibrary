package internet_store.core.domain;

import internet_store.ordering.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Client client;
    private List<Product> productsInCart;
    private long id;
    private int orderNumber;
    private String orderDate;
    private BigDecimal totalSum;
    private OrderStatus orderStatus;

    public Order(Client client) {
        this.client = client;
    }
}