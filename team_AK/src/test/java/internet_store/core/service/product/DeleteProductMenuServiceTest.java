package internet_store.core.service.product;


import internet_store.core.domain.Product;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;

import internet_store.database.interfaces.ProductDatabase;
import internet_store.database.product_database.ProductDatabaseImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DeleteProductMenuServiceTest {
    ProductDatabase productDatabase = new ProductDatabaseImpl();
    DeleteProductService deleteService = new DeleteProductService();

    @Test
    public void shouldReturnNoErrors() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("100"));
        productDatabase.addProduct(product);

        DeleteProductResponse response = deleteService.execute(new DeleteProductRequest(productDatabase, product, 1L));
        assertEquals(1, response.getId());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteProductResponse response = deleteService.execute(new DeleteProductRequest(productDatabase, new Product(), -1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteProductResponse response = deleteService.execute(new DeleteProductRequest(productDatabase, new Product(),
        5L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        DeleteProductResponse response = deleteService.execute(new DeleteProductRequest(productDatabase, new Product(), 0L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}