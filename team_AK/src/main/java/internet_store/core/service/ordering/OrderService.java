package internet_store.core.service.ordering;

import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.core.domain.ProductInCart;
import internet_store.core.operation.Tax;
import internet_store.core.persistence.CartRepository;
import internet_store.core.persistence.OrderRepository;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.cart.TotalSumCartService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private CartProductsCountService countService;
    @Autowired
    private TotalSumCartService totalSumCartService;
    @Autowired
    private CreateOrderNumberService numberService;
    @Autowired
    private Tax tax;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusService orderStatusService;
    @Getter
    @Setter
    private Client client;
    private String orderNumber;
    private BigDecimal totalSumInCart;
    private BigDecimal taxAmount;
    private BigDecimal total;

    public Order createOrder() {
        Order order = new Order();
        totalSumInCart = totalSumCartService.calculateTotalSum();
        if (numberService.getOrderHaveNumber()) {
            orderNumber = numberService.getFullOrderNumber();
        } else {
            orderNumber = numberService.createOrderNumber();
        }

        taxAmount = tax.getTaxAmount(totalSumInCart);
        total = tax.getAmountWithTax(totalSumInCart);
        order.setNumber(orderNumber);
        order.setDate(new Date());
        order.setClient(getClient());
        order.setSum(totalSumInCart);
        order.setTax(taxAmount);
        order.setTotal(total);
        return order;
    }

    public void saveOrder() {
        List<ProductInCart> itemsForOrder = cartRepository.itemsForOrder();
        itemsForOrder.forEach(productInCart -> {
            Order orderForSave = new Order();
            orderForSave.setNumber(orderNumber);
            orderForSave.setDate(new Date());
            orderForSave.setClient(getClient());
            orderForSave.setCart(productInCart);
            productInCart.setOrdered(true);
            orderForSave.setSum(totalSumInCart);
            orderForSave.setTax(taxAmount);
            orderForSave.setTotal(total);
            orderForSave.setStatus("ORDER RECEIVED");
            productInCart.getProduct().setQuantity(newCartQuantity(productInCart));
            orderRepository.saveAndFlush(orderForSave);
        });
        orderStatusService.changeOrderStatus(orderNumber, "ORDER RECEIVED");
    }

    private long newCartQuantity(ProductInCart productInCart) {
        return productInCart.getProduct().getQuantity() - productInCart.getQuantity();
    }

    public List<ProductInCart> getAllItemsFromCart() {
        return cartRepository.itemsForOrder();
    }

    public boolean isCanMakeOrder() {
        return client != null && countService.getCartCount() != 0;
    }
}