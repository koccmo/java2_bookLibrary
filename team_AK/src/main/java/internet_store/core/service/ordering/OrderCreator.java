package internet_store.core.service.ordering;

import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.core.domain.Product;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.client_database.InnerClientDatabase;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.date_formats.DateCreator;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DIComponent
public class OrderCreator {
    @DIDependency
    InnerClientDatabase clientDatabase;
    @DIDependency
    InnerCartDatabase cartDatabase;
    @DIDependency
    InnerOrderDatabase orderDatabase;

    public OrderCreator() {
    }

    public OrderCreator(InnerClientDatabase clientDatabase, InnerCartDatabase cartDatabase, InnerOrderDatabase orderDatabase) {
        this.cartDatabase = cartDatabase;
        this.clientDatabase = clientDatabase;
        this.orderDatabase = orderDatabase;
    }

    public void createOrder(long clientId) {
        DateCreator dateCreator = new DateCreator();

        Client client = clientDatabase.findById(clientId);

        Order order = new Order(client);
        order.setProductsInCart(new ArrayList<>(cartDatabase.getCart()));
        order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
        order.setOrderDate(dateCreator.createShortDateFormat());
        order.setTotalSum(orderTotalSum());
        orderDatabase.addOrder(order);

        System.out.println("Order process finished");

        cartDatabase.clearCart();
    }

    private BigDecimal orderTotalSum() {
        BigDecimal sum = new BigDecimal("0.00");
        List<Product> allProductsInCart = cartDatabase.getCart();
        for (Product product : allProductsInCart) {
            sum = sum.add(product.getSum());
        }
        return sum;
    }
}