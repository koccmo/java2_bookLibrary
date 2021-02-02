package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.response.product.DeleteProductResponse;
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
    public void notValidDelete() {

        DeleteProductByOtherRequest deleteProductByOtherRequest = new DeleteProductByOtherRequest("", "",
                null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("delete", "Not valid input for delete"));

        Mockito.when(deleteByOtherRequestValidator.validate(deleteProductByOtherRequest)).thenReturn(errors);

        DeleteByOtherResponse deleteByOtherResponse = (DeleteByOtherResponse) deleteByOtherService.execute(deleteProductByOtherRequest);
        assertEquals(deleteByOtherResponse.hasErrors(), true);
        assertEquals(deleteByOtherResponse.getErrors().size(), 1);
        assertEquals(deleteByOtherResponse.getErrors().get(0).getField(), "delete");

    }
}

