package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.response.cart.DeleteProductFromCartResponse;
import internet_store.core.service.cart.DeleteProductFromCartService;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DeleteProductFromInnerCartDatabaseServiceTest {


    @Before
    public void startUp() {

    }

    @Test
    public void shouldReturnNoErrors() {
        InnerCartDatabase cart = new InnerCartDatabaseImpl();
        DeleteProductFromCartService service = new DeleteProductFromCartService(cart);
        InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(new BigDecimal("5"));
        product.setPrice(new BigDecimal("100"));
        productDatabase.addProduct(product);

        cart.addProductToCart(product);

        DeleteProductFromCartResponse response = service.execute(new DeleteProductFromCartRequest(1L));
        assertEquals(1L, response.getId());
    }

    @Test
    public void shouldReturnError_1() {
        InnerCartDatabase cart = new InnerCartDatabaseImpl();
        DeleteProductFromCartService service = new DeleteProductFromCartService(cart);

        DeleteProductFromCartResponse response = service.execute(new DeleteProductFromCartRequest(-1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        InnerCartDatabase cart = new InnerCartDatabaseImpl();
        DeleteProductFromCartService service = new DeleteProductFromCartService(cart);

        DeleteProductFromCartResponse response = service.execute(new DeleteProductFromCartRequest(5L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        InnerCartDatabase cart = new InnerCartDatabaseImpl();
        DeleteProductFromCartService service = new DeleteProductFromCartService(cart);

        DeleteProductFromCartResponse response = service.execute(new DeleteProductFromCartRequest(0L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}