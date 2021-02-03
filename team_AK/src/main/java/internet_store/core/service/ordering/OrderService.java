package internet_store.core.service.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Cart;
import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.core.operation.Tax;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.cart.TotalSumCartService;
import internet_store.core.validate.NumberValidator;
import internet_store.database.interfaces.CartDatabase;
import internet_store.database.interfaces.ClientDatabase;
import internet_store.persistence.CartRepository;
import internet_store.persistence.OrderRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    CartProductsCountService countService;
    @Autowired
    ClientDatabase clientDatabase;
    @Autowired
    CartDatabase cartDatabase;
    @Autowired
    TotalSumCartService totalSumCartService;
    @Autowired
    CreateOrderNumberService numberService;
    @Autowired
    Tax tax;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Getter
    @Setter
    private Client client;
    private String orderNumber;
    private BigDecimal totalSumInCart;
    private BigDecimal taxAmount;
    private BigDecimal total;

    public OrderService(ClientDatabase clientDatabase, CartDatabase cartDatabase) {
        this.clientDatabase = clientDatabase;
        this.cartDatabase = cartDatabase;
    }

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
        List<Cart> itemsForOrder = cartRepository.itemsForOrder();
        itemsForOrder.forEach(i -> {
            Order orderForSave = new Order();
            orderForSave.setNumber(orderNumber);
            orderForSave.setDate(new Date());
            orderForSave.setClient(getClient());
            orderForSave.setCart(i);
            i.setOrdered(true);
            orderForSave.setSum(totalSumInCart);
            orderForSave.setTax(taxAmount);
            orderForSave.setTotal(total);
            orderForSave.setStatus("ORDER_RECEIVED");
            i.getProduct().setQuantity(i.getProduct().getQuantity() - i.getQuantity());
            orderRepository.saveAndFlush(orderForSave);
        });
    }

    public List<Cart> getAllItemsFromCart() {
        return cartRepository.itemsForOrder();
    }

    public OrderResponse execute(OrderRequest orderRequest) {
        NumberValidator<?> numberValidator = new NumberValidator<>(orderRequest.getId());

        List<CoreError> errors = numberValidator.validate();

        if (cartDatabase.isCartDatabaseEmpty()) {
            errors.add(new CoreError("Cart error ", "cart empty"));
        }

        if (!(isIdExist(orderRequest.getId()))) {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new OrderResponse(orderRequest.getId());
        }
        return new OrderResponse(errors);
    }

    public boolean isCanMakeOrder() {
        return client != null && countService.getCartCount() != 0;
    }

    private boolean isIdExist(long id) {
        return clientDatabase.isIdExist(id);
    }
}