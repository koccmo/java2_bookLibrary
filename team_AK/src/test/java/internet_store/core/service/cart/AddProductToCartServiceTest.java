package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AddProductToCartServiceTest {
    private final InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
    private final InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
    private final AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);

    @Test
    public void shouldReturnErrorProductNoExist() {
        AddProductToCartRequest request = new AddProductToCartRequest(1L,
                new BigDecimal("100"));
        AddProductToCartResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("Wrong Id", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorProductExistInCart() {
        AddProductToCartRequest request = new AddProductToCartRequest(1L,
                new BigDecimal("100"));
        Product product = new Product();
        product.setId(1L);
        productDatabase.addProduct(product);
        cartDatabase.addProductToCart(product);
        AddProductToCartResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product error ", response.getErrors().get(0).getField());
        assertEquals("Product exist in cart", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        AddProductToCartRequest request = new AddProductToCartRequest(1L,
                new BigDecimal("100"));
        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("5.35"));
        product.setQuantity(new BigDecimal("150"));
        productDatabase.addProduct(product);
        AddProductToCartResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getId());
        assertEquals(new BigDecimal("100"), cartDatabase.getCart().get(0).getQuantity());
    }
}