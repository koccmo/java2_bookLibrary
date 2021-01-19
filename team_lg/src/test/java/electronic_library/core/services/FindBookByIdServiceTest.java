package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibrary;
import electronic_library.core.domain.Book;
import electronic_library.core.requests.FindBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.FindBookByIdResponse;
import electronic_library.core.services.validators.FindBookByIdValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FindBookByIdServiceTest {
    @Mock
    ElectronicLibrary electronicLibrary;
    @Mock
    FindBookByIdValidator validator;
    @InjectMocks
    FindBookByIdService service;

    @Test
    public void shouldResponseIsValid() {
        FindBookByIdRequest request = new FindBookByIdRequest("1");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(electronicLibrary.findBookById(1L)).thenReturn(Optional.of(
                new Book("aaa", "aaa", new BigDecimal("10.00"),2001)));
        FindBookByIdResponse response = service.findBookByIdResponse(request);
        assertEquals(Optional.of(
                new Book("aaa", "aaa", new BigDecimal("10.00"),2001)),
                response.getFindBookById());
    }
    @Test
    public void shouldResponseWithErrorWhenNotValidFromString() {
        FindBookByIdRequest request = new FindBookByIdRequest("id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Book ID", "Should be valid."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindBookByIdResponse response = service.findBookByIdResponse(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Book ID", response.getErrors().get(0).getErrorField());
        assertEquals("Should be valid.", response.getErrors().get(0).getErrorMessage());
    }
    @Test
    public void shouldResponseWithErrorWhenNotValidFromNull() {
        FindBookByIdRequest request = new FindBookByIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Book ID", "Should not be empty."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindBookByIdResponse response = service.findBookByIdResponse(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Book ID", response.getErrors().get(0).getErrorField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getErrorMessage());
    }
}