package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.FindByIdRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindByIdRequestValidatorTest {

    FindByIdRequestValidator findByIdRequestValidator = new FindByIdRequestValidator();

    @Test
    public void testNotValidId(){

        CoreError expectedError = new CoreError("id", "Not valid input for id");

        FindByIdRequest findByIdRequest = new FindByIdRequest(-9);
        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        FindByIdRequest findByIdRequest = new FindByIdRequest(5);
        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

        assertTrue(errors.size() == 0);
    }

}
