package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.database.interfaces.ProductDatabase;
import internet_store.database.product_database.ProductDatabaseImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class AddProductServiceTest {
    private final ProductDatabase productDatabase = new ProductDatabaseImpl();
    private final AddProductService service = new AddProductService();
    private Product product;

    @Before
    public void startUp() {
        product = new Product();
    }

    @Test
    public void shouldReturnNoError() {
        product.setId(1L);
        product.setTitle("Example");
        product.setDescription("Test");
        product.setPrice(new BigDecimal("1.55"));
        product.setQuantity(5L);

        AddProductResponse response = service.execute(new AddProductRequest(productDatabase, product));
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnErrorNoTitle() {
        AddProductResponse response = service.execute(new AddProductRequest(productDatabase, product));
        assertTrue(response.hasErrors());
        assertEquals("Title input error: ", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorNoDescription() {
        product.setTitle("Test");
        AddProductResponse response = service.execute(new AddProductRequest(productDatabase, product));
        assertTrue(response.hasErrors());
        assertEquals("Description input error: ", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorQuantityLessZero() {
        product.setTitle("Test");
        product.setDescription("Test");
        product.setQuantity(-5L);
        AddProductResponse response = service.execute(new AddProductRequest(productDatabase, product));
        assertTrue(response.hasErrors());
        assertEquals("Quantity input error: ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorPriceLessZero() {
        product.setTitle("Test");
        product.setDescription("Test");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("-1.55"));
        AddProductResponse response = service.execute(new AddProductRequest(productDatabase, product));
        assertTrue(response.hasErrors());
        assertEquals("Price input error: ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorDuplicateExist() {
        product.setTitle("Test");
        product.setDescription("Test");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("3.15"));
        productDatabase.addProduct(product);

        Product product1 = new Product();
        product1.setTitle("Test");
        product1.setDescription("Test");
        product1.setQuantity(5L);
        product1.setPrice(new BigDecimal("2.00"));

        AddProductResponse response = service.execute(new AddProductRequest(productDatabase, product1));
        assertTrue(response.hasErrors());
        assertEquals("Add command error: ", response.getErrors().get(0).getField());
        assertEquals("Record exist in database", response.getErrors().get(0).getMessage());
    }
}