package internet_store_tests.core.services_tests.product;

import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.services.product.DeleteByOtherService;
import internet_store.core.services.product.validators.DeleteByOtherRequestValidator;
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

public class DeleteByOtherServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private DeleteByOtherRequestValidator deleteByOtherRequestValidator;
    @InjectMocks
    DeleteByOtherService deleteByOtherService;

    @Test
    public void notValidRequestToDeleteTest() {

        DeleteProductByOtherRequest deleteProductByOtherRequest = new DeleteProductByOtherRequest("", "",
                null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("delete", "Not valid input for delete"));

        Mockito.when(deleteByOtherRequestValidator.validate(deleteProductByOtherRequest)).thenReturn(errors);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(deleteProductByOtherRequest);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "delete");
    }

    @Test
    public void noTitleInDatabaseTest() {

        DeleteProductByOtherRequest firstRequest = new DeleteProductByOtherRequest("Apple","",
                null,null);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database","database doesn't contain product with title Apple");
        errors.add(expectedError);

        Mockito.when(deleteByOtherRequestValidator.validate(firstRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsTitle("Apple")).thenReturn(false);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(firstRequest);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
    }
/*
    @Test
    public void noDescriptionInDatabaseTest() {

        DeleteProductByOtherRequest firstRequest = new DeleteProductByOtherRequest("","Green",
                null,null);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database","database doesn't contain product with description Green");
        errors.add(expectedError);

        Mockito.when(deleteByOtherRequestValidator.validate(firstRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsDescription("Green")).thenReturn(false);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(firstRequest);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
    }

    @Test
    public void noPriceInDatabaseTest() {

        DeleteProductByOtherRequest firstRequest = new DeleteProductByOtherRequest("","",
                1,3);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database","database doesn't contain product with price 2");
        errors.add(expectedError);

        Mockito.when(deleteByOtherRequestValidator.validate(firstRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsPrice(2)).thenReturn(false);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(firstRequest);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
    }

 */
}

