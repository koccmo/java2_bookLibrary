package internet_store.core.service.add_product_to_cart;

import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.service.cart.AddProductToCartService;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddProductToInnerCartDatabaseImplServiceTest {

    private static final Product product = new Product();
    private static final InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
    private final InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();

    @BeforeClass
    public static void startUp() {
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(new BigDecimal("5"));
        product.setPrice(new BigDecimal("100"));
        productDatabase.addProduct(product);
    }

    @Test
    public void shouldReturnNoErrors() {
        AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, new BigDecimal("1")));
        assertNull(response.getErrors());
        assertEquals(1, response.getId());
    }

    @Test
    public void shouldReturnError_NoId() {
        AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(2L, new BigDecimal("1")));

        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("Wrong Id", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_QualityEqualZero() {
        AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);
        product.setQuantity(new BigDecimal("0"));

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, new BigDecimal("1")));

        assertEquals("Add to cart error ", response.getErrors().get(0).getField());
        assertEquals("Product quantity is zero", response.getErrors().get(0).getMessage());
        assertEquals("Quantity error ", response.getErrors().get(1).getField());
        assertEquals("No more product's quantity", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnError_QualityLessZero_1() {
        AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);

        product.setQuantity(new BigDecimal("5"));

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, new BigDecimal("6")));

        assertEquals("Quantity error ", response.getErrors().get(0).getField());
        assertEquals("No more product's quantity", response.getErrors().get(0).getMessage());
    }
}