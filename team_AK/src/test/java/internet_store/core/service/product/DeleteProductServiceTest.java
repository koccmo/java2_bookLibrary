package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DeleteProductServiceTest {
    private final InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
    private final DeleteProductService service = new DeleteProductService(productDatabase);

    @Test
    public void shouldReturnNoError() {
        Product product = new Product();
        product.setId(1L);
        productDatabase.addProduct(product);
        DeleteProductResponse response = service.execute(new DeleteProductRequest(1L));
        assertEquals(1, response.getId());
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteProductResponse response = service.execute(new DeleteProductRequest(-1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteProductResponse response = service.execute(new DeleteProductRequest(25L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}