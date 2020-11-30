package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddProductToCartServiceTest {
    @Mock
    private InnerProductDatabase productDatabase;
    @Mock
    private InnerCartDatabase cartDatabase;
    @InjectMocks
    private AddProductToCartService service;

    @Test
    public void shouldReturnErrorProductNoExist() {
        AddProductToCartRequest request = new AddProductToCartRequest(1L,
                new BigDecimal("100"));
        Mockito.when(productDatabase.isIdExist(request.getId())).thenReturn(false);
        Mockito.when(cartDatabase.isIdExist(request.getId())).thenReturn(false);
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
        Mockito.when(productDatabase.isIdExist(request.getId())).thenReturn(false);
        Mockito.when(cartDatabase.isIdExist(request.getId())).thenReturn(true);
        AddProductToCartResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product error ", response.getErrors().get(0).getField());
        assertEquals("Product exist in cart", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
        InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
        AddProductToCartService service = new AddProductToCartService(productDatabase, cartDatabase);

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