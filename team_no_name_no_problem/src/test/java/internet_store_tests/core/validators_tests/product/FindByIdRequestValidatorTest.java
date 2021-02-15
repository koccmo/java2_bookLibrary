package internet_store_tests.core.validators_tests.product;

import internet_store.core.requests.product.FindProductByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.product.validators.FindByIdRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindByIdRequestValidatorTest {

    FindByIdRequestValidator findByIdRequestValidator = new FindByIdRequestValidator();

    @Test
    public void testNotValidId(){

        CoreError expectedError = new CoreError("id", "Not valid input for id");

        FindProductByIdRequest findByIdRequest = new FindProductByIdRequest(-9L);
        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        FindProductByIdRequest findByIdRequest = new FindProductByIdRequest(5L);
        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

        assertTrue(errors.size() == 0);
    }

}
