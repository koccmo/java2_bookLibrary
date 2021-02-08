package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.core.domain.ProductInCart;
import internet_store.core.operation.Arithmetic;
import internet_store.core.persistence.CartRepository;
import internet_store.core.persistence.ProductRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddToCartServiceTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    CartRepository cartRepository;
    @Mock
    Arithmetic arithmetic;
    @InjectMocks
    AddToCartService addToCartService;

    @Test
    public void shouldReturn_NoError() {
        ProductInCart productInCart = new ProductInCart();
        Product product = new Product();
        product.setTitle("Product");
        product.setQuantity(5L);
        product.setCategory(1);
        product.setDescription("Description");
        product.setPrice(new BigDecimal("45.12"));

        Mockito.when(productRepository.findByTitle("Product")).thenReturn(product);
        Mockito.when(arithmetic.multiplyBigDecimalAndRound(new BigDecimal("2"),
                new BigDecimal("45.12"), 2)).thenReturn(new BigDecimal("90.24"));

        AddProductToCartResponse response = addToCartService.execute(new AddProductToCartRequest(2, "Product"));

        assertEquals(new BigDecimal("90.24"), response.getProductSum());
    }
}