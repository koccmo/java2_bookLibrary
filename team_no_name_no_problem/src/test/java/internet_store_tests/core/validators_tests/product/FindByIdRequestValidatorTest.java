package internet_store_tests.core.validators_tests.product;

import internet_store.core.requests.product.SearchProductByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.product.validators.SearchByIdRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindByIdRequestValidatorTest {

    SearchByIdRequestValidator findByIdRequestValidator = new SearchByIdRequestValidator();

    @Test
    public void testNotValidId(){

        CoreError expectedError = new CoreError("id", "Not valid input for id");

        SearchProductByIdRequest findByIdRequest = new SearchProductByIdRequest(-9L);
        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        SearchProductByIdRequest findByIdRequest = new SearchProductByIdRequest(5L);
        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

        assertTrue(errors.size() == 0);
    }

}
