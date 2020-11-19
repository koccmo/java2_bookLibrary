package lesson_3.core.service.add_product_to_cart;

import lesson_3.ProductListApplication;
import lesson_3.core.domain.Product;
import lesson_3.core.request.add_product_to_cart.AddProductToCartRequest;
import lesson_3.core.response.add_product_to_cart.AddProductToCartResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddProductToCartServiceTest {

    private static final Product product = new Product();

    @BeforeClass
    public static void startUp() {
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5);
        product.setPrice(new BigDecimal("100"));
        ProductListApplication.productDatabase.addProduct(product);
    }

    @Test
    public void shouldReturnNoErrors() {
        AddProductToCartService service = new AddProductToCartService(ProductListApplication.productDatabase,
                ProductListApplication.cart);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, 1));
        assertNull(response.getErrors());
        assertEquals(1, response.getId());
    }

    @Test
    public void shouldReturnError_NoId() {
        AddProductToCartService service = new AddProductToCartService(ProductListApplication.productDatabase,
                ProductListApplication.cart);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(2L, 1));

        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("Wrong Id", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_QualityEqualZero() {
        AddProductToCartService service = new AddProductToCartService(ProductListApplication.productDatabase,
                ProductListApplication.cart);
        product.setQuantity(0);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, 1));

        assertEquals("Add to cart error ", response.getErrors().get(0).getField());
        assertEquals("Product quantity is zero", response.getErrors().get(0).getMessage());
        assertEquals("Quantity error ", response.getErrors().get(1).getField());
        assertEquals("No more product's quantity", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnError_QualityEqualZero_1() {
        AddProductToCartService service = new AddProductToCartService(ProductListApplication.productDatabase,
                ProductListApplication.cart);

        product.setQuantity(5);

        AddProductToCartResponse response = service.execute(new AddProductToCartRequest(1L, 6));

        assertEquals("Quantity error ", response.getErrors().get(0).getField());
        assertEquals("No more product's quantity", response.getErrors().get(0).getMessage());
    }
}