package internet_store.core.service.cart;

import internet_store.configuration.StoreConfiguration;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.database.cart_database.CartDatabaseImpl;
import internet_store.database.product_database.ProductDatabaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StoreConfiguration.class)
@WebAppConfiguration
public class AddToCartServiceTest {
    @Autowired
    ProductDatabaseImpl productDatabase;
    @Autowired
    CartDatabaseImpl cartDatabase;
    @Autowired
    AddToCartService service;

    @Test
    public void shouldReturnErrorProductNoExist() {
        AddProductToCartRequest request = new AddProductToCartRequest(2L, 100, cartDatabase, "");
        AddProductToCartResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("Wrong Id", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        AddProductToCartRequest request = new AddProductToCartRequest(1L, 100L, cartDatabase, "");
        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("5.35"));
        product.setQuantity(100L);
        productDatabase.addProduct(product);
        AddProductToCartResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        long result = cartDatabase.getCart().get(0).getQuantity();
        assertEquals(100L, result);
    }
}