package internet_store.lesson_6.core.services.validators;

import internet_store.lesson_6.core.requests.ChangeProductNameRequest;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.services.validators.ChangeProductNameValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChangeProductNameValidatorTest {

    private ChangeProductNameValidator validator;

    @Before
    public void setUp(){
        validator = new ChangeProductNameValidator();
    }

    @Test
    public void shouldReturnNoErrors() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "tv");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product new name");
        assertEquals(errors.get(0).getMessage(), "Should not be empty.");
    }
    @Test
    public void shouldReturnErrorWhenNameIsEmpty() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product new name");
        assertEquals(errors.get(0).getMessage(), "Should not be empty.");
    }

}