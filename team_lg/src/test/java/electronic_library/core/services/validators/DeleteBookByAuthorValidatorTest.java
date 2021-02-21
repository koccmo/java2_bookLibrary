package electronic_library.core.services.validators;

import electronic_library.core.requests.book.DeleteBookByAuthorRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.book.validators.DeleteBookByAuthorValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeleteBookByAuthorValidatorTest {

    private DeleteBookByAuthorValidator validator;

    @Before
    public void setUp () {
        validator = new DeleteBookByAuthorValidator();
    }

    @Test
    public void valid() {
        DeleteBookByAuthorRequest request = new DeleteBookByAuthorRequest("aaa");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void notValidWhenBookAuthorIsNull() {
        DeleteBookByAuthorRequest request = new DeleteBookByAuthorRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("bookAuthor", errors.get(0).getErrorField());
        assertEquals("Book author should not be empty!", errors.get(0).getErrorMessage());
    }

    @Test
    public void notValidWhenBookAuthorIsEmpty() {
        DeleteBookByAuthorRequest request = new DeleteBookByAuthorRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("bookAuthor", errors.get(0).getErrorField());
        assertEquals("Book author should not be empty!", errors.get(0).getErrorMessage());
    }
}
