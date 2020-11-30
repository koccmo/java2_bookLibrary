package internet_store.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.ChangeProductService;
import internet_store.core.services.product.ChangeProductValidator;
import internet_store.database.product.ProductDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)

public class ChangeTitleServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private ChangeProductValidator changeProductValidator;
    @InjectMocks
    ChangeProductService changeProductService;

    @Test
    public void changeTitleOfProductRequestTest() {

        Product laptop = new Product("Laptop","Samsung",400);
        ChangeProductRequest request1 = new ChangeProductRequest(3L,"Notebook",
                "Samsung",400);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"database");
    }
}