package lesson_3.core.service.delete_product;


import lesson_3.ProductListApplication;
import lesson_3.core.domain.Product;
import lesson_3.core.request.delete_product.DeleteProductRequest;
import lesson_3.core.response.delete_product.DeleteProductResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DeleteProductMenuServiceTest {
    DeleteProductService deleteService = new DeleteProductService(ProductListApplication.productDatabase);

    @Test
    public void shouldReturnNoErrors() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5);
        product.setPrice(new BigDecimal("100"));
        ProductListApplication.productDatabase.addProduct(product);

        DeleteProductResponse response = deleteService.execute(new DeleteProductRequest(1L));
        assertEquals(1, response.getId());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteProductResponse response = deleteService.execute(new DeleteProductRequest(-1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteProductResponse response = deleteService.execute(new DeleteProductRequest(5L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}