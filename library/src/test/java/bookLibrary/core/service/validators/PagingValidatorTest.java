package bookLibrary.core.service.validators;

import bookLibrary.core.request.Paging;
import bookLibrary.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PagingValidatorTest {
    private PagingValidator validator = new PagingValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberNullPageSizeValid() {
        Paging paging = new Paging(null, 1);
        List<CoreError> errors = validator.validate(paging);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenPageSizeNullPageNumberValid() {
        Paging paging = new Paging(1, null);
        List<CoreError> errors = validator.validate(paging);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("PageSize", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberZeroAndPageSizeValid() {
        Paging paging = new Paging(0, 1);
        List<CoreError> errors = validator.validate(paging);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenPageSizeZeroPageNumberValid() {
        Paging paging = new Paging(1, 0);
        List<CoreError> errors = validator.validate(paging);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("PageSize", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
    }

    @Test
    public void ShouldReturnErrorsWhenPageNumberAndPageSizeValueNotValid() {
        Paging paging = new Paging(0, 0);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(2, errors.size());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
        assertEquals("PageSize", errors.get(1).getField());
        assertEquals("Must be over 0!", errors.get(1).getDescription());
    }

    @Test
    public void shouldReturnErrorsWhenPageSizeAndPageNumberNull() {
        Paging paging = new Paging(null, null);
        List<CoreError> errors = validator.validate(paging);
        assertTrue(errors.isEmpty());
    }

}