package internet_store.core.service.cart;

import internet_store.configuration.StoreConfiguration;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.response.cart.DeleteProductFromCartResponse;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StoreConfiguration.class)
@WebAppConfiguration
public class DeleteProductFromInnerCartDatabaseServiceTest {
    private final Product product = new Product();
    @Autowired
    ProductDatabaseImpl productDatabase;
    @Autowired
    CartDatabaseImpl cartDatabase;
    @Autowired
    DeleteProductFromCartService deleteService;
    @Autowired
    AddToCartService service;

    @Test
    public void shouldReturnNoErrors() {
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("100"));
        productDatabase.setId(1L);
        productDatabase.addProduct(product);
        service.execute(new AddProductToCartRequest(1L, 4, cartDatabase, product.getTitle()));

        DeleteProductFromCartResponse response = deleteService.execute(new DeleteProductFromCartRequest(1L, cartDatabase, "Title"));
        assertEquals(1L, response.getId());
        productDatabase.clear();
    }

    @Test
    public void shouldReturnError_1() {
        DeleteProductFromCartResponse response = deleteService.execute(new DeleteProductFromCartRequest(-1L, cartDatabase, ""));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteProductFromCartResponse response = deleteService.execute(new DeleteProductFromCartRequest(5L, cartDatabase, ""));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        DeleteProductFromCartResponse response = deleteService.execute(new DeleteProductFromCartRequest(0L, cartDatabase, ""));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}