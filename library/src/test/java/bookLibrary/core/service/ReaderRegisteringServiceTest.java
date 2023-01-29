package bookLibrary.core.service;

import bookLibrary.core.dataBase.ReaderRepository;
import bookLibrary.core.request.ReaderRegisteringRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.ReaderRegisteringResponse;
import bookLibrary.core.service.validators.ReaderRegisteringValidation;
import bookLibrary.matchers.ReaderMather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReaderRegisteringServiceTest {
    @Mock private ReaderRegisteringValidation validator;
    @Mock private ReaderRepository dataBase;
    @InjectMocks private ReaderRegisteringService service;

    @Test
    public void shouldReturnResponseWithErrorValidationFails() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("", "AL", "1");
        when(validator.validate(request, dataBase)).thenReturn(List.of(new CoreError("name",
                "Can`t be Empty or Null!")));
        ReaderRegisteringResponse response = service.execute(request);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithErrorFromValidator() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("", "Au", "1");
        when(validator.validate(request, dataBase)).thenReturn(List.of(new CoreError("Name",
                "Can`t be Empty or Null!")));
        ReaderRegisteringResponse response = service.execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Name", response.getErrors().get(0).getField());
        assertEquals("Can`t be Empty or Null!", response.getErrors().get(0).getDescription());
    }

    @Test
    public void shouldNotDoActionWithDataBaseThenValidationFail() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("", "AAA", "1");
        when(validator.validate(request, dataBase)).thenReturn(List.of(new CoreError("Name",
                "Can`t be Empty or Null!")));
        ReaderRegisteringResponse response = service.execute(request);
        verifyNoInteractions(dataBase);
    }

    @Test
    public void shouldReturnResponseNotErrorsWhenRequestValid() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("a", "b", "1");
        when(validator.validate(request, dataBase)).thenReturn(List.of());
        ReaderRegisteringResponse response = service.execute(request);
        assertFalse(response.hasError());
    }

    @Test
    public void shouldRegisterReaderWhenRequestValid() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("a","b", "1");
        when(validator.validate(request, dataBase)).thenReturn(List.of());
        ReaderRegisteringResponse response = service.execute(request);
        assertTrue(response.isReaderRegistered());
    }

    @Test
    public void shouldRegisterReaderWhenValidationValid() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("a", "b", "1");
        when(validator.validate(request, dataBase)).thenReturn(List.of());
        ReaderRegisteringResponse response = service.execute(request);
        verify(dataBase).save(argThat(new ReaderMather("a", "b")));
    }
}