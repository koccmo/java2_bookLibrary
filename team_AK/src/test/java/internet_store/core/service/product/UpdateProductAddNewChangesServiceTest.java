package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.database.product_database.ProductDatabaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductAddNewChangesServiceTest {
    @Mock
    private ProductDatabaseImpl productDatabase;
    @InjectMocks
    private UpdateProductAddNewChangesService service;

    @Test
    public void shouldUpdateProduct() {
        Product product = new Product();
        product.setId(0L);
        product.setTitle("Test");
        product.setDescription("Test");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("3"));

        service.execute(new AddProductRequest(productDatabase, product));

        Mockito.verify(productDatabase, Mockito.times(1))
                .updateProduct(0, product);
    }

    @Test
    public void shouldNoUpdateProductHaveErrors() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test");
        service.execute(new AddProductRequest(productDatabase, product));

        Mockito.verifyNoInteractions(productDatabase);
    }
}