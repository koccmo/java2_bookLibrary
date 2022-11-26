package bookLibrary.core.service.validators;

import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchBooksRequestFieldValidatorTest {

    private SearchBooksRequestFieldValidator validator = new SearchBooksRequestFieldValidator();

    @Test
    public void shouldNotReturnErrorWhetAuthorProvided() {
        SearchBooksRequest request = new SearchBooksRequest("Author", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWhetTitleProvided() {
        SearchBooksRequest request = new SearchBooksRequest("", "Title");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldNotReturnErrorWhetAuthorAndTitleProvided() {
        SearchBooksRequest request = new SearchBooksRequest("Author", "Title");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenAuthorAndTitleIsEmpty() {
        SearchBooksRequest request = new SearchBooksRequest("", "");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        assertEquals("Author", errors.get(0).getField());
        assertEquals("Field Author Must be Fill UP!", errors.get(0).getDescription());
        assertEquals("Title", errors.get(1).getField());
        assertEquals("Field Title Must Be Fill UP!", errors.get(1).getDescription());
    }
}