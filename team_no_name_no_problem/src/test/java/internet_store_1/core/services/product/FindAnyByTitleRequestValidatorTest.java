package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.FindAnyByTitleRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindAnyByTitleRequestValidatorTest {

    FindAnyByTitleRequestValidator findAnyByTitleRequestValidator = new FindAnyByTitleRequestValidator();

    @Test
    public void testInvalidTitle(){
        CoreError expectedError = new CoreError("title", "Not valid input for title");

        FindAnyByTitleRequest findAllByTitleRequest = new FindAnyByTitleRequest("");
        List<CoreError> errors = findAnyByTitleRequestValidator.validate(findAllByTitleRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        FindAnyByTitleRequest findAllByTitleRequest = new FindAnyByTitleRequest("Title");
        List<CoreError> errors = findAnyByTitleRequestValidator.validate(findAllByTitleRequest);

        assertTrue(errors.size() == 0);
    }

}
