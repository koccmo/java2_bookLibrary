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
public class TakeBookBookIdValidatorTest {
    @Mock
    private ReaderBookRepository repository;
    @InjectMocks
    private TakeBookBookIdValidator validator;


    @Test
    public void validRequest() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(repository.hasBookInDataBaseCheckById(Long.valueOf(request.getBookId()))).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void bookIdEmpty() {
        TakeBookRequest request = new TakeBookRequest("1", "", new Date());
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("BookID", errors.get(0).getField());
        assertEquals("Must be filled UP!", errors.get(0).getDescription());
    }

    @Test
    public void bookIdContainOnlyNumbers() {
        TakeBookRequest request = new TakeBookRequest("1", "a1", new Date());
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("BookID", errors.get(0).getField());
        assertEquals("Must contain only Digit!", errors.get(0).getDescription());
    }

    @Test
    public void libraryDoNotHaveBook() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(repository.hasBookInDataBaseCheckById(Long.valueOf(request.getBookId())))
                .thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("BookId", errors.get(0).getField());
        assertEquals("Book not found in DataBase", errors.get(0).getDescription());
    }
}