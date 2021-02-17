package internet_store_tests.core.validators_tests.product;

import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.requests.product.DeleteProductByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.product.validators.DeleteProductByOtherRequestValidator;
import internet_store.core.services.product.validators.DeleteProductByIdRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeleteProductRequestValidatorTest {

    DeleteProductByIdRequestValidator deleteProductRequestValidator = new DeleteProductByIdRequestValidator();
    DeleteProductByOtherRequestValidator deleteByOtherRequestValidator = new DeleteProductByOtherRequestValidator();

    @Test
    public void testNotValidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");

        DeleteProductByIdRequest deleteProductRequest = new DeleteProductByIdRequest(-8L);
        List<CoreError> errors = deleteProductRequestValidator.validate(deleteProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){
        DeleteProductByIdRequest deleteProductRequest = new DeleteProductByIdRequest(5L);
        List<CoreError> errors = deleteProductRequestValidator.validate(deleteProductRequest);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testNotValidTitle() {
        CoreError expectedError = new CoreError("title", "Not valid input for title");

        DeleteProductByOtherRequest deleteProductByOtherRequest = new DeleteProductByOtherRequest("","",
                0,0);
        List<CoreError> errors = deleteByOtherRequestValidator.validate(deleteProductByOtherRequest);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testNotValidDescription() {
        CoreError expectedError = new CoreError("title", "Not valid input for title");

        DeleteProductByOtherRequest deleteProductByOtherRequest = new DeleteProductByOtherRequest("","",
                0,0);
        List<CoreError> errors = deleteByOtherRequestValidator.validate(deleteProductByOtherRequest);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void testNotValidPriceRange() {
        CoreError expectedError = new CoreError("title", "Not valid input for title");

        DeleteProductByOtherRequest deleteProductByOtherRequest = new DeleteProductByOtherRequest("","",
                0,0);
        List<CoreError> errors = deleteByOtherRequestValidator.validate(deleteProductByOtherRequest);

        assertTrue(errors.isEmpty());
    }
}
