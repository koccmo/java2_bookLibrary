package internet_store.core.service.ordering;

import internet_store.core.domain.Cart;
import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.database.interfaces.CartDatabase;
import internet_store.database.interfaces.ClientDatabase;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.date_formats.DateCreator;
import internet_store.integration.mail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderCreator {
    @Autowired
    private final ClientDatabase clientDatabase;
    @Autowired
    private final CartDatabase cartDatabase;
    @Autowired
    private final InnerOrderDatabase orderDatabase;
    @Autowired
    private EmailServiceImpl emailService;

    public OrderCreator(ClientDatabase clientDatabase, CartDatabase cartDatabase, InnerOrderDatabase orderDatabase) {
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

        if (emailService != null) {
            emailService.sendSimpleMessage(client.getEmail(), "Order confirmed", createMailBodyText(order));
        }

        cartDatabase.clearCart();
    }

    private BigDecimal orderTotalSum() {
        BigDecimal sum = new BigDecimal("0.00");
        List<Cart> allProductsInCart = cartDatabase.getCart();
        // TODO: 25.01.2021 must take data from cart
//        for (Product product : allProductsInCart) {
//            sum = sum.add(product.getSum());
//        }
        return sum;
    }

    private String createMailBodyText(Order order) {
        return "Information about order number: " + order.getOrderNumber() + "\n"
                + "Order date: " + order.getOrderDate() + "\n"
                + "Total sum: " + order.getTotalSum() + "\n"
                + "Order status: " + order.getOrderStatus().toString();

    }
}