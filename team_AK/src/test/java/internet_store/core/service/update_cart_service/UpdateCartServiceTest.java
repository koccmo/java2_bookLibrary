package internet_store.core.service.update_cart_service;

import internet_store.core.domain.Product;
import internet_store.core.request.update_cart_request.UpdateCartRequest;
import internet_store.core.response.update_cart_response.UpdateCartResponse;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UpdateCartServiceTest {
    private static final Product product = new Product();
    private static final Product productInCart = new Product();
    private static final InnerProductDatabaseImpl productDatabase = new InnerProductDatabaseImpl();
    private static final InnerCartDatabaseImpl cartDatabase = new InnerCartDatabaseImpl();

    @BeforeClass
    public static void startUp() {
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(new BigDecimal("5"));
        product.setPrice(new BigDecimal("100"));
        productDatabase.addProduct(product);

        productInCart.setId(1L);
        productInCart.setTitle("Title");
        productInCart.setDescription("Description");
        productInCart.setQuantity(new BigDecimal("2"));
        productInCart.setPrice(new BigDecimal("100"));
        cartDatabase.addProductToCart(productInCart);
    }

    @Test
    public void shouldReturnNoErrors() {
        UpdateCartService service = new UpdateCartService(productDatabase, cartDatabase);

        UpdateCartResponse response = service.execute(new UpdateCartRequest(1L, new BigDecimal("3")));
        assertEquals(1L, response.getId());
        BigDecimal updatedResult = productInCart.getQuantity();
        assertEquals(new BigDecimal("3"), updatedResult);

    }

    @Test
    public void shouldReturnError_NoId() {
        UpdateCartService service = new UpdateCartService(productDatabase, cartDatabase);

        UpdateCartResponse response = service.execute(new UpdateCartRequest(2L, new BigDecimal("1")));

        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("Wrong Id", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_QuantityEqualZero() {
        UpdateCartService service = new UpdateCartService(productDatabase, cartDatabase);

        UpdateCartResponse response = service.execute(new UpdateCartRequest(1L, new BigDecimal("5")));

        assertEquals(1L, response.getId());
        BigDecimal updatedResult = productInCart.getQuantity();
        assertEquals(new BigDecimal("5"), updatedResult);
    }

    @Test
    public void shouldReturnError_QuantityLessZero_1() {
        UpdateCartService service = new UpdateCartService(productDatabase, cartDatabase);

        UpdateCartResponse response = service.execute(new UpdateCartRequest(1L, new BigDecimal("60")));

        assertEquals("Quantity error ", response.getErrors().get(0).getField());
        assertEquals("No more product's quantity", response.getErrors().get(0).getMessage());
    }
}