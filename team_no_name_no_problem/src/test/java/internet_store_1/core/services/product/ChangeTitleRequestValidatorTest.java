package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.ChangeTitleRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ChangeTitleRequestValidatorTest {

    ChangeTitleRequestValidator changeTitleRequestValidator = new ChangeTitleRequestValidator();

    @Test
    public void testInvalidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");

        ChangeTitleRequest changeTitleRequest = new ChangeTitleRequest(-8, "Bob and dog");
        List<CoreError> errors = changeTitleRequestValidator.validate(changeTitleRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidDescription(){
        CoreError expectedError = new CoreError("title", "Not Valid input for title");

        ChangeTitleRequest changeTitleRequest = new ChangeTitleRequest(1, "");
        List<CoreError> errors = changeTitleRequestValidator.validate(changeTitleRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidDescriptionAndId(){
        CoreError expectedError1 = new CoreError("title", "Not Valid input for title");
        CoreError expectedError2 = new CoreError("id", "Not valid input for id");

        ChangeTitleRequest changeTitleRequest = new ChangeTitleRequest(-2, "");
        List<CoreError> errors = changeTitleRequestValidator.validate(changeTitleRequest);

        assertTrue(errors.size() == 2);
        assertTrue(errors.contains(expectedError1));
        assertTrue(errors.contains(expectedError2));
    }

    @Test
    public void testValidInput(){

        ChangeTitleRequest changeTitleRequest = new ChangeTitleRequest(2, "Description");
        List<CoreError> errors = changeTitleRequestValidator.validate(changeTitleRequest);

        assertTrue(errors.size() == 0);
    }

}
