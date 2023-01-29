package bookLibrary.core.service;

import bookLibrary.core.dataBase.ReaderBookRepository;
import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.TakeBookResponse;
import bookLibrary.core.service.validators.TakeBookValidator;
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
public class TakeBookServiceTest {
    @Mock
    private TakeBookValidator validator;
    @Mock
    private ReaderBookRepository repository;
    @InjectMocks
    private TakeBookService service;

    @Test
    public void shouldReturnResponseWithTrueWhenValidRequest() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(validator.validate(request)).thenReturn(List.of());
        TakeBookResponse response = service.execute(request);
        assertTrue(response.isBookToken());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenNotValidRequest() {
        TakeBookRequest request = new TakeBookRequest("1", "2", new Date());
        when(validator.validate(request))
                .thenReturn(List.of(new CoreError("Book", "Can`t take, book token from Library")));
        TakeBookResponse response = service.execute(request);
        assertFalse(response.getErrors().isEmpty());
        assertTrue(response.hasError());
        assertEquals(1, response.getErrors().size());
        assertEquals("Book", response.getErrors().get(0).getField());
        assertEquals("Can`t take, book token from Library", response.getErrors().get(0).getDescription());
    }
}