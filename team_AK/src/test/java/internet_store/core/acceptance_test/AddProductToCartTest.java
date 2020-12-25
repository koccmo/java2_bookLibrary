package internet_store.core.acceptance_test;

import internet_store.core.domain.Client;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.service.cart.AddProductToCartService;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.client_database.InnerClientDatabase;
import internet_store.database.client_database.InnerClientDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AddProductToCartTest {
    InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();
    InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
    InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
    AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);

    @Test
    public void addProductToCart() {

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
        AddProductToCartRequest request = new AddProductToCartRequest(1L, new BigDecimal("3"));

        AddProductToCartResponse response = service.execute(request);

        assertEquals(1L, response.getId());
        assertEquals(new BigDecimal("3"), cartDatabase.getCart().get(0).getQuantity());
        assertEquals(new BigDecimal("1.28"), cartDatabase.getCart().get(0).getPrice());
        assertEquals(new BigDecimal("3.84"), cartDatabase.getCart().get(0).getSum());
    }
}