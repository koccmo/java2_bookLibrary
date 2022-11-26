package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.DeleteBookResponse;
import bookLibrary.core.service.validators.DeleteBookValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class DeleteBookServiceTest {
    @Mock private DeleteBookValidation validation;
    @Mock private DataBase dataBase;
    @InjectMocks private DeleteBookService service;

    @Test
    public void shouldReturnResponseWithErrorWhenValidationFail() {
        DeleteBookRequest request = new DeleteBookRequest(null);
        when(validation.validate(request,dataBase))
                .thenReturn(List.of(new CoreError("Id", "Must be fill UP!")));
        DeleteBookResponse response = service.execute(request);
        assertTrue(response.hasError());
    }
    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFail() {
        DeleteBookRequest request = new DeleteBookRequest(null);
        when(validation.validate(request, dataBase))
                .thenReturn(List.of(new CoreError("Id", "Must be fill UP!")));
        DeleteBookResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(1, response.getErrors().size());
        assertEquals("Id", response.getErrors().get(0).getField());
        assertEquals("Must be fill UP!", response.getErrors().get(0).getDescription());
    }

    @Test
    public void notInvokeDataBaseWhenRequestValidationFail() {
        DeleteBookRequest request = new DeleteBookRequest(null);
        when(validation.validate(request, dataBase))
                .thenReturn(List.of(new CoreError("Id", "Must be fill UP!")));
        service.execute(request);
        verifyNoInteractions(dataBase);
    }

    @Test
    public void captorTest() {
        DeleteBookRequest request = new DeleteBookRequest("1");
        when(validation.validate(request, dataBase)).thenReturn(List.of());
        service.execute(request);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(dataBase).deleteBook(captor.capture());
        Long argument = captor.getValue();
        assertEquals(argument.longValue(), 1L);
    }

    @Test
    public void responseNoErrorsWhenRequestValid() {
        DeleteBookRequest request = new DeleteBookRequest("1");
        when(validation.validate(request, dataBase)).thenReturn(List.of());
        DeleteBookResponse response = service.execute(request);
        assertFalse(response.hasError());
    }

    @Test
    public void shouldRemoveBookWhenRequestValid() {
        DeleteBookRequest request = new DeleteBookRequest("1");
        when(validation.validate(request, dataBase)).thenReturn(List.of());
        DeleteBookResponse response = service.execute(request);
        assertNotNull(response);
        assertTrue(response.isBookDeleted());
    }

}