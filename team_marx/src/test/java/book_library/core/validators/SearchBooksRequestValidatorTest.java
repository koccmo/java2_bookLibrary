package book_library.core.validators;

import book_library.core.requests.SearchBooksRequest;
import book_library.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchBooksRequestValidatorTest {
    private SearchBooksRequestValidator validator = new SearchBooksRequestValidator();

    @Test
    public void successWithTitle() {
        SearchBooksRequest request = new SearchBooksRequest( "Title", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void successWithAuthor() {
        SearchBooksRequest request = new SearchBooksRequest( null, "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void successWithTitleAndAuthor() {
        SearchBooksRequest request = new SearchBooksRequest( "Title", "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenTitleAndAuthorIsEmpty() {
        SearchBooksRequest request = new SearchBooksRequest( "", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleAndAuthorIsNull() {
        SearchBooksRequest request = new SearchBooksRequest( null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmptyAndAuthorIsNull() {
        SearchBooksRequest request = new SearchBooksRequest( "", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleIsNullAndAuthorIsEmpty() {
        SearchBooksRequest request = new SearchBooksRequest( null, "");
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }


}