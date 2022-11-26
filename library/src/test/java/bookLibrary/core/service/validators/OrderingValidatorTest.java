package bookLibrary.core.service.validators;

import bookLibrary.core.request.Ordering;
import bookLibrary.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderingValidatorTest {
    private OrderingValidator validator = new OrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderByEmpty() {
        Ordering ordering = new Ordering("", "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("OrderBy", errors.get(0).getField());
        assertEquals("Must be equal Author or Title!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionEmpty() {
        Ordering ordering = new Ordering("Author", "");
        List<CoreError> errors = validator.validate(ordering);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("OrderDirection", errors.get(0).getField());
        assertEquals("Must be choosing one of Direction!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenOrderByNotValidValue() {
        Ordering ordering = new Ordering("Name", "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("OrderBy", errors.get(0).getField());
        assertEquals("Must be equal Author or Title!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionNotValidValue() {
        Ordering ordering = new Ordering("Author", "FromTopDown");
        List<CoreError> errors = validator.validate(ordering);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("OrderDirection", errors.get(0).getField());
        assertEquals("Must be choosing one of Direction!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorsWhenOrderByAndOrderDirectionEmpty() {
        Ordering ordering = new Ordering("", "");
        List<CoreError> errors = validator.validate(ordering);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        assertEquals("OrderBy", errors.get(0).getField());
        assertEquals("OrderDirection", errors.get(1).getField());
        assertEquals("Must be equal Author or Title!", errors.get(0).getDescription());
        assertEquals("Must be choosing one of Direction!", errors.get(1).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenOrderByAndOrderDirectionNotValidValue() {
        Ordering ordering = new Ordering("Name", "Top");
        List<CoreError> errors = validator.validate(ordering);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        assertEquals("OrderBy", errors.get(0).getField());
        assertEquals("OrderDirection", errors.get(1).getField());
        assertEquals("Must be equal Author or Title!", errors.get(0).getDescription());
        assertEquals("Must be choosing one of Direction!", errors.get(1).getDescription());
    }

}