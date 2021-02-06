package electronic_library.core.services.validators;

import electronic_library.core.requests.book.DeleteBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.book.validators.DeleteBookByIdValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeleteBookByIdValidatorTest {

    private DeleteBookByIdValidator validator;

    @Before

    public void setUp () {
        validator = new DeleteBookByIdValidator();
    }

    @Test
    public void shouldValidWhenIdIsNotNull() {
        DeleteBookByIdRequest request = new DeleteBookByIdRequest(1L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldNotValidWhenIdIsNull() {
        DeleteBookByIdRequest request = new DeleteBookByIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Book ID", errors.get(0).getErrorField());
        assertEquals("not be empty!", errors.get(0).getErrorMessage());
    }
    @Test
    public void shouldNotValidWhenIdIsEmpty() {
        DeleteBookByIdRequest request = new DeleteBookByIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Book ID", errors.get(0).getErrorField());
        assertEquals("not be empty!", errors.get(0).getErrorMessage());
    }
}
