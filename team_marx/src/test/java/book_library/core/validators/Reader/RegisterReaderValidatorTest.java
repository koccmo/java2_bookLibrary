package book_library.core.validators.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.responses.CoreError;
import book_library.matchers.ReaderMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class RegisterReaderValidatorTest {

    @Mock
    private ReaderRepository database;
    @InjectMocks
    private RegisterReaderValidator validator;

    @Test
    public void success() {
        RegisterReaderRequest request = new RegisterReaderRequest("FirstName", "LastName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenFirstNameIsNull() {
        RegisterReaderRequest request = new RegisterReaderRequest(null, "LastName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("firstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenFirstNameIsEmpty() {
        RegisterReaderRequest request = new RegisterReaderRequest("", "LastName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("firstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsNull() {
        RegisterReaderRequest request = new RegisterReaderRequest("FirstName", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("lastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsEmpty() {
        RegisterReaderRequest request = new RegisterReaderRequest("FirstName", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("lastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenFirstNameAndLastNameIsNull() {
        RegisterReaderRequest request = new RegisterReaderRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(2, errors.size());
        assertEquals("firstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
        assertEquals("lastName", errors.get(1).getField());
        assertEquals("Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenReaderAlreadyIsRegistered() {
        RegisterReaderRequest request = new RegisterReaderRequest("FirstName", "LastName");

        List<CoreError> validationErrors = new ArrayList<>();
        validationErrors.add(new CoreError("First name and last name", "This reader already is registered"));
        Mockito.when(database.hasTheSameReaderInDatabase(any())).thenReturn(true);

        List<CoreError> errors = validator.validate(request);
        Mockito.verify(database).hasTheSameReaderInDatabase(
                argThat(new ReaderMatcher("FirstName", "LastName")));
        assertEquals(1, errors.size());
        assertEquals("First name and last name", errors.get(0).getField());
        assertEquals("This reader already is registered", errors.get(0).getMessage());
    }


}