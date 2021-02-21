package electronic_library.core.services.validators;

import electronic_library.core.requests.book.DeleteBookByTitleRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.book.validators.DeleteBookByTitleValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeleteBookByTitleValidatorTest {
    private DeleteBookByTitleValidator validator;

    @Before
    public void setUp () {
        validator = new DeleteBookByTitleValidator();
    }

    @Test
    public void valid() {
        DeleteBookByTitleRequest request = new DeleteBookByTitleRequest("aaa");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void notValidWhenBookTitleIsNull() {
        DeleteBookByTitleRequest request = new DeleteBookByTitleRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("bookTitle", errors.get(0).getErrorField());
        assertEquals("Book title should not be empty!", errors.get(0).getErrorMessage());
    }

    @Test
    public void notValidWhenBookTitleIsEmpty() {
        DeleteBookByTitleRequest request = new DeleteBookByTitleRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("bookTitle", errors.get(0).getErrorField());
        assertEquals("Book title should not be empty!", errors.get(0).getErrorMessage());
    }
}
