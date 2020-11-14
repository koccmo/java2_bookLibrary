package internet_store.core.services.product;

import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeleteProductValidatorTest {

    DeleteProductValidator deleteProductValidator = new DeleteProductValidator();

    @Test
    public void testNotValidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");

        DeleteProductRequest deleteProductRequest = new DeleteProductRequest(-8);
        List<CoreError> errors = deleteProductValidator.validate(deleteProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){
        DeleteProductRequest deleteProductRequest = new DeleteProductRequest(5);
        List<CoreError> errors = deleteProductValidator.validate(deleteProductRequest);

        assertTrue(errors.isEmpty());
    }
}
