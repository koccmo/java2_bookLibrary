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
public class TakeBookTokenBookTest {
    @Mock
    private ReaderBookRepository repository;
    @InjectMocks
    private TakeBookTokenBook tokenBook;

    @Test
    public void bookTokenFromLibrary() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(repository.checkReturnDateEmpty(Long.valueOf(request.getBookId())))
                .thenReturn(true);
        List<CoreError> errors = tokenBook.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void bookInLibrary() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(repository.checkReturnDateEmpty(Long.valueOf(request.getBookId())))
                .thenReturn(false);
        List<CoreError> errors = tokenBook.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Book", errors.get(0).getField());
        assertEquals("Token from Library", errors.get(0).getDescription());
    }

}