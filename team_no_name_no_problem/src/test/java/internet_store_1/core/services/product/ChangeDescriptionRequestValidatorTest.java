package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.ChangeDescriptionRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChangeDescriptionRequestValidatorTest {

    ChangeDescriptionRequestValidator changeDescriptionRequestValidator = new ChangeDescriptionRequestValidator();

    @Test
    public void testInvalidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");

        ChangeDescriptionRequest changeDescriptionRequest = new ChangeDescriptionRequest(-8, "Bob and dog");
        List<CoreError> errors = changeDescriptionRequestValidator.validate(changeDescriptionRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidDescription(){
        CoreError expectedError = new CoreError("description", "Not valid input for description");

        ChangeDescriptionRequest changeDescriptionRequest = new ChangeDescriptionRequest(1, "");
        List<CoreError> errors = changeDescriptionRequestValidator.validate(changeDescriptionRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidDescriptionAndId(){
        CoreError expectedError1 = new CoreError("description", "Not valid input for description");
        CoreError expectedError2 = new CoreError("id", "Not valid input for id");

        ChangeDescriptionRequest changeDescriptionRequest = new ChangeDescriptionRequest(-2, "");
        List<CoreError> errors = changeDescriptionRequestValidator.validate(changeDescriptionRequest);

        assertTrue(errors.size() == 2);
        assertTrue(errors.contains(expectedError1));
        assertTrue(errors.contains(expectedError2));
    }

    @Test
    public void testValidInput(){

        ChangeDescriptionRequest changeDescriptionRequest = new ChangeDescriptionRequest(2, "Description");
        List<CoreError> errors = changeDescriptionRequestValidator.validate(changeDescriptionRequest);

        assertTrue(errors.size() == 0);
    }

}
