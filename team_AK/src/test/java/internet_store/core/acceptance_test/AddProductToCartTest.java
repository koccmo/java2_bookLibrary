package internet_store.core.acceptance_test;

import internet_store.core.domain.Client;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.service.cart.AddToCartService;
import internet_store.database.cart_database.CartDatabaseImpl;
import internet_store.database.interfaces.ClientDatabase;
import internet_store.database.client_database.ClientDatabaseImpl;
import internet_store.database.interfaces.CartDatabase;
import internet_store.database.interfaces.ProductDatabase;
import internet_store.database.product_database.ProductDatabaseImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AddProductToCartTest {
    ClientDatabase clientDatabase = new ClientDatabaseImpl();
    ProductDatabase productDatabase = new ProductDatabaseImpl();
    CartDatabase cartDatabase = new CartDatabaseImpl();
    AddToCartService service = new AddToCartService();

    @Ignore
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
        product.setQuantity(15L);
        product.setPrice(new BigDecimal("1.28"));
        productDatabase.addProduct(product);
        AddProductToCartRequest request = new AddProductToCartRequest(1L, 3L,cartDatabase,"");

        AddProductToCartResponse response = service.execute(request);

        assertEquals(1L, response.getId());
        long result = cartDatabase.getCart().get(0).getQuantity();
        assertEquals(3L, result);
        assertEquals(new BigDecimal("1.28"), cartDatabase.getCart().get(0).getPrice());
        //assertEquals(new BigDecimal("3.84"), cartDatabase.getCart().get(0).getSum());
    }
}