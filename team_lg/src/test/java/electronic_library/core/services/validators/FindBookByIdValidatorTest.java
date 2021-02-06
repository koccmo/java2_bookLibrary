package electronic_library.core.services.validators;

import electronic_library.core.requests.book.FindBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.book.validators.FindBookByIdValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindBookByIdValidatorTest {

    private FindBookByIdValidator validator;

    @Before
    public void setUp () {
        validator = new FindBookByIdValidator();
    }

    @Test
    public void shouldValidWhenIdIsNotNullAndEmpty() {
        FindBookByIdRequest request = new FindBookByIdRequest("1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldNotValidWhenIdIsNull() {
        FindBookByIdRequest request = new FindBookByIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Book ID", errors.get(0).getErrorField());
        assertEquals("not be empty.", errors.get(0).getErrorMessage());
    }

    @Test
    public void shouldNotValidWhenIdIsEmpty() {
        FindBookByIdRequest request = new FindBookByIdRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Book ID", errors.get(0).getErrorField());
        assertEquals("not be empty.", errors.get(0).getErrorMessage());
    }

    @Test
    public void shouldNotValidWhenIdNotNumeric() {
        FindBookByIdRequest request = new FindBookByIdRequest("aaa");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Book ID", errors.get(0).getErrorField());
        assertEquals("Should be valid.", errors.get(0).getErrorMessage());
    }
}
