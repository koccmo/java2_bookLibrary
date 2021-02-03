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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StoreConfiguration.class)
@WebAppConfiguration
public class AddProductToInnerCartDatabaseImplServiceTest {
    private final Product product = new Product();
    @Autowired
    ProductDatabaseImpl productDatabase;
    @Autowired
    CartDatabaseImpl cartDatabase;
    @Autowired
    AddToCartService service;

    @Test
    public void shouldReturnNoErrors() {
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("100"));
        productDatabase.addProduct(product);
        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, 1L, cartDatabase, ""));
        assertFalse(response.hasErrors());
        productDatabase.clear();
    }

    @Test
    public void shouldReturnError_NoId() {
        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(2L, 1L, cartDatabase, ""));

        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("Wrong Id", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_QualityEqualZero() {

        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(0L);
        product.setPrice(new BigDecimal("100"));
        productDatabase.setId(1L);
        productDatabase.addProduct(product);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, 5L, cartDatabase, ""));

        assertEquals("Quantity error ", response.getErrors().get(0).getField());
        assertEquals("Product quantity is zero", response.getErrors().get(0).getMessage());
        productDatabase.clear();
    }

    @Test
    public void shouldReturnError_QualityLessZero_1() {
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("100"));
        productDatabase.setId(1L);
        productDatabase.addProduct(product);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, 6L, cartDatabase, "'"));

        assertEquals("Quantity error ", response.getErrors().get(0).getField());
        assertEquals("No more product's quantity", response.getErrors().get(0).getMessage());
        productDatabase.clear();
    }
}