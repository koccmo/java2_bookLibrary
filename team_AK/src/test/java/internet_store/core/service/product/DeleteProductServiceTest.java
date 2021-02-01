package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.database.interfaces.ProductDatabase;
import internet_store.database.product_database.ProductDatabaseImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DeleteProductServiceTest {
    private final ProductDatabase productDatabase = new ProductDatabaseImpl();
    private final DeleteProductService service = new DeleteProductService();

    @Test
    public void shouldReturnNoError() {
        Product product = new Product();
        product.setId(1L);
        productDatabase.addProduct(product);
        DeleteProductResponse response = service.execute(new DeleteProductRequest(productDatabase, product, 1L));
        assertEquals(1, response.getId());
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteProductResponse response = service.execute(new DeleteProductRequest(productDatabase, new Product(), -1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteProductResponse response = service.execute(new DeleteProductRequest(productDatabase, new Product(), 25L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}