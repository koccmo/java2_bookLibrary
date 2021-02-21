package electronic_library.core.services;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.requests.book.DeleteBookByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.book.DeleteBookByIdResponse;
import electronic_library.core.services.book.DeleteBookByIdService;
import electronic_library.core.services.book.validators.DeleteBookByIdValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DeleteBookByIdServiceTest {

    @Mock
    private ElectronicLibraryRepository electronicLibrary;

    @Mock
    private DeleteBookByIdValidator validator;

    @InjectMocks
    private DeleteBookByIdService service;

    @Test
    public void shouldDeleteBookByIdFromElectronicLibrary() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(electronicLibrary.deleteBookById(1L)).thenReturn(true);
        DeleteBookByIdRequest request = new DeleteBookByIdRequest(1L);
        DeleteBookByIdResponse response = service.deleteBookByIdResponse(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isBookRemoved());
    }

    @Test
    public void shouldReturnErrorWhenBookIdNotProvided() {
        DeleteBookByIdRequest request = new DeleteBookByIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Book ID", "not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteBookByIdResponse response = service.deleteBookByIdResponse(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "Book ID");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "not be empty!");
    }

    @Test
    public void notValidDeleteBookByIdRequest() {
        DeleteBookByIdRequest request = new DeleteBookByIdRequest(-2L);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Book ID", "not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteBookByIdResponse response = service.deleteBookByIdResponse(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
    }
}