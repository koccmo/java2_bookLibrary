package book_library.core.validators;

import book_library.core.requests.AddBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.validators.AddBookValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddBookValidatorTest {

    private AddBookValidator validator = new AddBookValidator();

    @Test
    public void success() {
        AddBookRequest request = new AddBookRequest("Title", "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldReturnErrorWhenTitleIsNull() {
        AddBookRequest request = new AddBookRequest(null, "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"title");
        assertEquals(errors.get(0).getMessage(),"Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        AddBookRequest request = new AddBookRequest("", "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"title");
        assertEquals(errors.get(0).getMessage(),"Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenAuthorIsNull() {
        AddBookRequest request = new AddBookRequest("Title", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"author");
        assertEquals(errors.get(0).getMessage(),"Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenAuthorIsEmpty() {
        AddBookRequest request = new AddBookRequest("Title", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"author");
        assertEquals(errors.get(0).getMessage(),"Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenTitleOrAuthorIsNullOrIsEmpty() {
        AddBookRequest request = new AddBookRequest(null, "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
        assertEquals(errors.get(1).getField(),"author");
        assertEquals(errors.get(1).getMessage(),"Must not be empty");
    }

}