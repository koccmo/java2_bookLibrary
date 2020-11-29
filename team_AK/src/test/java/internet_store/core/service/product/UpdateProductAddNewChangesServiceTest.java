package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.database.product_database.InnerProductDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductAddNewChangesServiceTest {
    @Mock
    private InnerProductDatabase productDatabase;
    @InjectMocks
    private UpdateProductAddNewChangesService service;

    @Test
    public void shouldUpdateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test");
        service.execute(new ArrayList<>(), product);

        Mockito.verify(productDatabase, Mockito.times(1))
                .updateProduct(0, product);
    }

    @Test
    public void shouldNoUpdateProductHaveErrors() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Test", "Test"));
        service.execute(errors, product);

        Mockito.verifyNoInteractions(productDatabase);
    }
}