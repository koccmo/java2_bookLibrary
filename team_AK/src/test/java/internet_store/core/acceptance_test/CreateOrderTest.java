package internet_store.core.acceptance_test;

import internet_store.core.domain.Client;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.request.ordering.OrderStatusRequest;
import internet_store.core.request.telegram.FindTelegramChatIdRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.core.service.cart.AddProductToCartService;
import internet_store.core.service.ordering.OrderCreator;
import internet_store.core.service.ordering.OrderService;
import internet_store.core.service.ordering.OrderStatus;
import internet_store.core.service.ordering.OrderStatusService;
import internet_store.core.service.telegram.FindTelegramChatIdService;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.client_database.InnerClientDatabase;
import internet_store.database.client_database.InnerClientDatabaseImpl;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.database.order_database.InnerOrderDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import internet_store.database.telegram_database.InnerTelegramDatabase;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreateOrderTest {
    InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();
    InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
    InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
    InnerOrderDatabase orderDatabase = new InnerOrderDatabaseImpl();

    @Test
	@Ignore
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
        AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);
        AddProductToCartResponse response = service.execute(request);

        assertEquals(1L, response.getId());
        assertEquals(new BigDecimal("3"), cartDatabase.getCart().get(0).getQuantity());
        assertEquals(new BigDecimal("1.28"), cartDatabase.getCart().get(0).getPrice());
        assertEquals(new BigDecimal("3.84"), cartDatabase.getCart().get(0).getSum());

        OrderRequest orderingRequest = new OrderRequest(client.getId());
        OrderService orderService = new OrderService(clientDatabase, cartDatabase);
        OrderResponse orderResponse = orderService.execute(orderingRequest);
        assertEquals(1L, orderResponse.getId());

        OrderCreator orderCreator = new OrderCreator(clientDatabase, cartDatabase, orderDatabase);
        orderCreator.createOrder(orderResponse.getId());
        assertEquals("Name", orderDatabase.getOrder().get(0).getClient().getName());
        assertEquals(new BigDecimal("1.28"), orderDatabase.getOrder().get(0).getProductsInCart().get(0).getPrice());
        assertEquals(new BigDecimal("3.84"), orderDatabase.getOrder().get(0).getProductsInCart().get(0).getSum());
        assertEquals(OrderStatus.ORDER_RECEIVED, orderDatabase.getOrder().get(0).getOrderStatus());
    }
}