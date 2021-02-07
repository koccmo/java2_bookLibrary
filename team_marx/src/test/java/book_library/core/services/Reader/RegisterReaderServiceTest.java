package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Reader.RegisterReaderResponse;
import book_library.core.validators.Reader.RegisterReaderValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private RegisterReaderValidator validator;

    @InjectMocks
    private RegisterReaderService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        RegisterReaderRequest request = new RegisterReaderRequest(null, "LastName");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("firstName", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        RegisterReaderResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("firstName", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());

        Mockito.verifyNoInteractions(readerRepository);
    }

}