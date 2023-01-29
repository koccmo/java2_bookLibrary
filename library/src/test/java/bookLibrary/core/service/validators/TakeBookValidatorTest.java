package bookLibrary.core.service.validators;

import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TakeBookValidatorTest {
    @Mock
    private TakeBookBookIdValidator bookIdValidator;
    @Mock
    private TakeBookReaderIdValidator readerIdValidator;
    @Mock
    private TakeBookTokenBook tokenBook;
    @InjectMocks
    private TakeBookValidator validator;

    @Test
    public void shouldReturnOneErrorBookNotFoundInLibrary() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(bookIdValidator.validate(request)).thenReturn(List.of());
        when(readerIdValidator.validate(request)).thenReturn(List.of());
        when(tokenBook.validate(request)).thenReturn(List.of(new CoreError("Book", "Token from Library")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("Book", errors.get(0).getField());
        assertEquals("Taken from Library", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnOneErrorWhenBookIdEmpty() {
        TakeBookRequest request = new TakeBookRequest("", "1", new Date());
        when(bookIdValidator.validate(request)).thenReturn(List.of(
                new CoreError("BookId", "Must be fill up!")));
        when(readerIdValidator.validate(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("BookId", errors.get(0).getField());
        assertEquals("Must be fill up!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnOneErrorWhenReaderIdEmpty() {
        TakeBookRequest request = new TakeBookRequest("", "2", new Date());
        when(bookIdValidator.validate(request)).thenReturn(List.of());
        when(readerIdValidator.validate(request)).thenReturn(List.of(
                new CoreError("ReaderId", "Field must be fill up!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("ReaderId", errors.get(0).getField());
        assertEquals("Field must be fill up!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnEmptyListWhenValidRequest() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(bookIdValidator.validate(request)).thenReturn(List.of());
        when(readerIdValidator.validate(request)).thenReturn(List.of());
        when(tokenBook.validate(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}