package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.ReaderBookRepository;
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
public class TakeBookReaderIdValidatorTest {
    @Mock
    private ReaderBookRepository repository;
    @InjectMocks
    private TakeBookReaderIdValidator validator;

    @Test
    public void validRequest() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(repository.hasReaderInDataBase(Long.valueOf(request.getReaderId()))).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void readerIdEmpty() {
        TakeBookRequest request = new TakeBookRequest("", "1", new Date());
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("ReaderId", errors.get(0).getField());
        assertEquals("Must be fill up!", errors.get(0).getDescription());
    }

    @Test
    public void readerIdMustContainOnlyDigits() {
        TakeBookRequest request = new TakeBookRequest("1a", "1", new Date());
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("ReaderId", errors.get(0).getField());
        assertEquals("Must contain only Numbers!", errors.get(0).getDescription());
    }

    @Test
    public void noReaderInDataBase() {
        TakeBookRequest request = new TakeBookRequest("1", "1", new Date());
        when(repository.hasReaderInDataBase(Long.valueOf(request.getReaderId()))).thenReturn(false);
        List<CoreError> errors = validator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals("Reader", errors.get(0).getField());
        assertEquals("Not found in DataBase", errors.get(0).getDescription());
    }
}