package internet_store.application.core.services.product.validators;


import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class FindByProductIdValidatorTest {

    private FindByProductIdValidator validator;

    @Before

    public void setUp () {
        validator = new FindByProductIdValidator();
    }

   @Test
    public void shallNotPassWhenIdIsNull() {
        FindByIdRequest request = new FindByIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should not be empty.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnNoErrorWhenIdIsNotNull() {
        FindByIdRequest request = new FindByIdRequest("1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shallNotPassWhenIdIsEmpty() {
        FindByIdRequest request = new FindByIdRequest(" ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should not be empty.", errors.get(0).getMessage());
    }

    @Test
    public void shallNotPassWhenIdIsNotNumber() {
        FindByIdRequest request = new FindByIdRequest("MISTAKE");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should be valid.", errors.get(0).getMessage());
    }

}