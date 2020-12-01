package internet_store.core.acceptance_test;

import internet_store.ApplicationContext;
import internet_store.core.domain.Client;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.request.ordering.OrderStatusRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.core.service.cart.AddProductToCartService;
import internet_store.core.service.ordering.OrderCreator;
import internet_store.core.service.ordering.OrderService;
import internet_store.core.service.ordering.OrderStatus;
import internet_store.core.service.ordering.OrderStatusService;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.client_database.InnerClientDatabase;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.database.product_database.InnerProductDatabase;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CreateOrderTest {
    ApplicationContext context = new ApplicationContext();
    InnerClientDatabase clientDatabase = context.getBean(InnerClientDatabase.class);
    InnerProductDatabase productDatabase = context.getBean(InnerProductDatabase.class);
    InnerCartDatabase cartDatabase = context.getBean(InnerCartDatabase.class);
    InnerOrderDatabase orderDatabase = context.getBean(InnerOrderDatabase.class);

    @Test
    public void createOrder() {

        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setEmail("test@test.lv");
        client.setPhoneNumber("29456321");
        clientDatabase.addClient(client);

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product");
        product.setDescription("Description");
        product.setQuantity(new BigDecimal("15"));
        product.setPrice(new BigDecimal("1.28"));
        productDatabase.addProduct(product);

        AddProductToCartRequest request = new AddProductToCartRequest(product.getId(), new BigDecimal("3"));
        AddProductToCartService service = context.getBean(AddProductToCartService.class);
        AddProductToCartResponse response = service.execute(request);

        assertEquals(1L, response.getId());
        assertEquals(new BigDecimal("3"), cartDatabase.getCart().get(0).getQuantity());
        assertEquals(new BigDecimal("1.28"), cartDatabase.getCart().get(0).getPrice());
        assertEquals(new BigDecimal("3.84"), cartDatabase.getCart().get(0).getSum());

        OrderRequest orderingRequest = new OrderRequest(client.getId());
        OrderService orderService = context.getBean(OrderService.class);
        OrderResponse orderResponse = orderService.execute(orderingRequest);
        assertEquals(1L, orderResponse.getId());

        OrderCreator orderCreator = context.getBean(OrderCreator.class);
        orderCreator.createOrder(orderResponse.getId());
        assertEquals("Name", orderDatabase.getOrder().get(0).getClient().getName());
        assertEquals(new BigDecimal("1.28"), orderDatabase.getOrder().get(0).getProductsInCart().get(0).getPrice());
        assertEquals(new BigDecimal("3.84"), orderDatabase.getOrder().get(0).getProductsInCart().get(0).getSum());

        OrderStatusRequest orderStatusRequest = new OrderStatusRequest(OrderStatus.ITEM_ORDERED_TO_STOCK, 1L);
        OrderStatusService orderStatusService = context.getBean(OrderStatusService.class);
        orderStatusService.execute(orderStatusRequest);
        assertEquals(OrderStatus.ITEM_ORDERED_TO_STOCK, orderDatabase.getOrder().get(0).getOrderStatus());

        OrderStatusRequest orderStatusRequest_2 = new OrderStatusRequest(OrderStatus.ORDER_WAITING_OFFICE, 1L);
        OrderStatusService orderStatusService_2 = context.getBean(OrderStatusService.class);
        orderStatusService_2.execute(orderStatusRequest_2);
        assertEquals(OrderStatus.ORDER_WAITING_OFFICE, orderDatabase.getOrder().get(0).getOrderStatus());
    }
}