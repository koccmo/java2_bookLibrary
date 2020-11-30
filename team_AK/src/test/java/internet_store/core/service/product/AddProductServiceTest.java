package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.database.product_database.InnerProductDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {
    private Product product;
    @Mock
    private InnerProductDatabase productDatabase;
    @InjectMocks
    private AddProductService service;

    @Before
    public void startUp() {
        product = new Product();
    }

    @Test
    public void shouldReturnNoError() {
        product.setId(1L);
        product.setTitle("Test");
        product.setDescription("Test");
        product.setPrice(new BigDecimal("1.55"));
        product.setQuantity(new BigDecimal("5"));

        AddProductResponse response = service.execute(new AddProductRequest(product));
        assertFalse(response.hasErrors());
        Mockito.verify(productDatabase, Mockito.times(1)).addProduct(product);
    }

    @Test
    public void shouldReturnErrorNoTitle() {
        AddProductResponse response = service.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("Title input error: ", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    public void shouldReturnErrorNoDescription() {
        product.setTitle("Test");
        AddProductResponse response = service.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("Description input error: ", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    public void shouldReturnErrorQuantityLessZero() {
        product.setTitle("Test");
        product.setDescription("Test");
        product.setQuantity(new BigDecimal("-5"));
        AddProductResponse response = service.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("Quantity input error: ", response.getErrors().get(0).getField());
        assertEquals("Negative number", response.getErrors().get(0).getMessage());
        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    public void shouldReturnErrorPriceLessZero() {
        product.setTitle("Test");
        product.setDescription("Test");
        product.setQuantity(new BigDecimal("5"));
        product.setPrice(new BigDecimal("-1.55"));
        AddProductResponse response = service.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("Price input error: ", response.getErrors().get(0).getField());
        assertEquals("Negative number", response.getErrors().get(0).getMessage());
        Mockito.verifyNoInteractions(productDatabase);
    }
}