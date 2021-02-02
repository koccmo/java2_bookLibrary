package internet_store.core.service.cart;

import internet_store.configuration.StoreConfiguration;
import internet_store.core.domain.Cart;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.UpdateCartRequest;
import internet_store.core.response.cart.UpdateCartResponse;
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
public class UpdateCartServiceTest {
    private static final Product product = new Product();
    private static final Cart productInCart = new Cart();
    @Autowired
    ProductDatabaseImpl productDatabase;
    @Autowired
    CartDatabaseImpl cartDatabase;
    @Autowired
    UpdateCartService service;

    public void startUp() {
        product.setTitle("Title");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("100"));
        productDatabase.setId(1L);
        productDatabase.addProduct(product);

        productInCart.setTitle("Title");
        productInCart.setQuantity(2L);
        productInCart.setPrice(new BigDecimal("100"));
        cartDatabase.setId(1L);
        cartDatabase.addProductToCart(productInCart);
    }

    @Test
    public void shouldReturnNoErrors() {
        startUp();
        UpdateCartResponse response = service.execute(new UpdateCartRequest(1L, 3L));
        long updatedResult = productInCart.getQuantity();
        assertFalse(response.hasErrors());
        cartDatabase.clearCart();
        productDatabase.clear();
    }

    @Test
    public void shouldReturnError_NoId() {
        UpdateCartResponse response = service.execute(new UpdateCartRequest(2L, 1L));

        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("Wrong Id", response.getErrors().get(0).getMessage());
        cartDatabase.clearCart();
        productDatabase.clear();
    }

    @Test
    public void shouldReturnError_QuantityEqualZero() {
        startUp();
        UpdateCartResponse response = service.execute(new UpdateCartRequest(1L, 6L));
        assertTrue(response.hasErrors());
        long updatedResult = productInCart.getQuantity();
        assertEquals(2L, updatedResult);
        cartDatabase.clearCart();
        productDatabase.clear();
    }

    @Test
    public void shouldReturnError_QuantityLessZero_1() {
        startUp();
        UpdateCartResponse response = service.execute(new UpdateCartRequest(1L, 60L));
        assertEquals("Quantity error ", response.getErrors().get(0).getField());
        assertEquals("No more product's quantity", response.getErrors().get(0).getMessage());
        cartDatabase.clearCart();
        productDatabase.clear();
    }
}