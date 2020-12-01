package internet_store.services.product;
/*
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.GetAllProductsService;
import internet_store.core.services.product.GetAllProductsValidator;
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

public class GetAllProductsServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private GetAllProductsValidator getAllProductsValidator;
    @InjectMocks
    GetAllProductsService getAllProductsService;

    @Test
    public void getAllProductsRequestTest() {

        GetProductsRequest request1 = new GetProductsRequest();

        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database", "Should not be empty!"));
        Mockito.when(getAllProductsValidator.validate(request1)).thenReturn(errors1);

        GetProductsResponse response = getAllProductsService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "title");
    }
}

 */