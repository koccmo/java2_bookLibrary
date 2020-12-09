package internet_store_tests.core.validators_tests.product;

import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.product.validators.ChangeProductValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChangeProductValidatorTest {

    ChangeProductValidator changeProductValidator = new ChangeProductValidator();

    @Test
    public void testNotValidId(){
        CoreError coreError = new CoreError("id", "Not valid input for id");

        ChangeProductRequest changeProductRequest =
                new ChangeProductRequest(-1L, "title", "description", 6);
        List<CoreError> errors = changeProductValidator.validate(changeProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPrice(){
        CoreError coreError = new CoreError("price", "Not valid input for price");

        ChangeProductRequest changeProductRequest =
                new ChangeProductRequest(1L, "title", "description", -6);
        List<CoreError> errors = changeProductValidator.validate(changeProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testValidRequest(){

        ChangeProductRequest changeProductRequest =
                new ChangeProductRequest(1L, "title", "description", 6);
        List<CoreError> errors = changeProductValidator.validate(changeProductRequest);

        assertTrue(errors.size() == 0);
    }

}
