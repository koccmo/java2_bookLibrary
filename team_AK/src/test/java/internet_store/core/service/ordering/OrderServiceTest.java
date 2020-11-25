package internet_store.core.service.ordering;

import internet_store.core.domain.Client;
import internet_store.core.domain.Product;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.client_database.InnerClientDatabase;
import internet_store.database.client_database.InnerClientDatabaseImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {
    private static final Product productInCart = new Product();
    private static final Client client = new Client();

    @Before
    public void startUp() {
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29336699");
        client.setEmail("a@a.lv");

        productInCart.setId(1L);
        productInCart.setTitle("Title");
        productInCart.setDescription("Description");
        productInCart.setQuantity(new BigDecimal("2"));
        productInCart.setPrice(new BigDecimal("100"));
    }

    @Test
    public void shouldReturnNoErrors() {
        final InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
        final InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();

        clientDatabase.addClient(client);
        cartDatabase.addProductToCart(productInCart);

        OrderService service = new OrderService(clientDatabase, cartDatabase);
        OrderResponse response = service.execute(new OrderRequest(1L));
        assertEquals(1L, response.getId());
    }

    @Test
    public void returnErrorIncorrectClientId() {
        final InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
        final InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();

        clientDatabase.addClient(client);
        cartDatabase.addProductToCart(productInCart);

        OrderService service = new OrderService(clientDatabase, cartDatabase);
        OrderResponse response = service.execute(new OrderRequest(5L));

        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnErrorCartNoExist() {
        final InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
        final InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();

        clientDatabase.addClient(client);

        OrderService service = new OrderService(clientDatabase, cartDatabase);
        OrderResponse response = service.execute(new OrderRequest(1L));

        assertEquals("Cart error ", response.getErrors().get(0).getField());
        assertEquals("cart empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnErrorCartAndClientIdNoExist() {
        final InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
        final InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();

        OrderService service = new OrderService(clientDatabase, cartDatabase);
        OrderResponse response = service.execute(new OrderRequest(1L));

        assertEquals("Cart error ", response.getErrors().get(0).getField());
        assertEquals("cart empty", response.getErrors().get(0).getMessage());
        assertEquals("Id error ", response.getErrors().get(1).getField());
        assertEquals("wrong ID", response.getErrors().get(1).getMessage());
    }
}