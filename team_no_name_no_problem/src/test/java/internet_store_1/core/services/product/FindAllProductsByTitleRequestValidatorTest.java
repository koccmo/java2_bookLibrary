package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.FindAllByTitleRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindAllProductsByTitleRequestValidatorTest {

    FindAllProductsByTitleRequestValidator findAllProductsByTitleRequestValidator = new FindAllProductsByTitleRequestValidator();

    @Test
    public void testInvalidTitle(){
        CoreError expectedError = new CoreError("title", "Not valid input for title");

        FindAllByTitleRequest findAllByTitleRequest = new FindAllByTitleRequest(null);
        List<CoreError> errors = findAllProductsByTitleRequestValidator.validate(findAllByTitleRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        FindAllByTitleRequest findAllByTitleRequest = new FindAllByTitleRequest("Description");
        List<CoreError> errors = findAllProductsByTitleRequestValidator.validate(findAllByTitleRequest);

        assertTrue(errors.size() == 0);
    }


}
