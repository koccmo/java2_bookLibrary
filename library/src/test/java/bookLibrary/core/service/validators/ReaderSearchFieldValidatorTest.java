package bookLibrary.core.service.validators;

import bookLibrary.core.request.SearchReaderRequest;
import bookLibrary.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReaderSearchFieldValidatorTest {


    @Test
    public void allDataIsValid() {
        SearchReaderRequest request = new SearchReaderRequest("A", "B");
        ReaderSearchFieldValidator validator = new ReaderSearchFieldValidator();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void nameIsEmptyLastNameValid() {
        SearchReaderRequest request = new SearchReaderRequest("", "B");
        ReaderSearchFieldValidator validator = new ReaderSearchFieldValidator();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void lastNameIsEmptyNameIsValid() {
        SearchReaderRequest request = new SearchReaderRequest("A", "");
        ReaderSearchFieldValidator validator = new ReaderSearchFieldValidator();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void nameAndLastNameEmpty() {
        SearchReaderRequest request = new SearchReaderRequest("", "");
        ReaderSearchFieldValidator validator = new ReaderSearchFieldValidator();
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "Name or Last Name");
        assertEquals(errors.get(0).getDescription(), "Must be fill up!");
    }

    @Test
    public void nameIsValidLastNameContainNumbers() {
        SearchReaderRequest request = new SearchReaderRequest("A", "B1");
        ReaderSearchFieldValidator validator = new ReaderSearchFieldValidator();
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "LastName");
        assertEquals(errors.get(0).getDescription(), "Must Contain only Letters!");
    }

    @Test
    public void lastNameIsValidNameContainNumbers() {
        SearchReaderRequest request = new SearchReaderRequest("12A", "B");
        ReaderSearchFieldValidator validator = new ReaderSearchFieldValidator();
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "Must Contain only Letters!");
    }

    @Test
    public void nameAndLastNameContainNumbers() {
        SearchReaderRequest request = new SearchReaderRequest("A1", "B1");
        ReaderSearchFieldValidator validator = new ReaderSearchFieldValidator();
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getDescription(), "Must Contain only Letters!");
        assertEquals(errors.get(1).getField(), "LastName");
        assertEquals(errors.get(1).getDescription(), "Must Contain only Letters!");
    }


}