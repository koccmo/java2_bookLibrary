package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibrary;
import electronic_library.core.matchers.BookMatcher;
import electronic_library.core.requests.AddBookRequest;
import electronic_library.core.responses.AddBookResponse;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.validators.AddBookValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceTest {
    @Mock
    private ElectronicLibrary electronicLibrary;

    @Mock
    private AddBookValidator validator;

    @InjectMocks
    private AddBookService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddBookRequest request = new AddBookRequest(null, "aaa", new BigDecimal("10.00"),2010);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("aaa", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "aaa");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "Must not be empty!");

        Mockito.verifyNoInteractions(electronicLibrary);
    }

    @Test
    public void shouldAddBookToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddBookRequest request = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"),2010);
        AddBookResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(electronicLibrary).saveBook(argThat(new BookMatcher("aaa","aaa")));
    }
}