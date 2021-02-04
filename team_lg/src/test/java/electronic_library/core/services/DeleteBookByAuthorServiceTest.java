package electronic_library.core.services;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.requests.book.DeleteBookByAuthorRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.book.DeleteBookByAuthorResponse;
import electronic_library.core.services.book.DeleteBookByAuthorService;
import electronic_library.core.services.book.validators.DeleteBookByAuthorValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DeleteBookByAuthorServiceTest {

    @Mock
    private ElectronicLibraryRepository electronicLibrary;

    @Mock
    private DeleteBookByAuthorValidator validator;

    @InjectMocks
    private DeleteBookByAuthorService service;

    @Test
    public void shouldReturnErrorWhenBookAuthorNotProvided() {
        DeleteBookByAuthorRequest request = new DeleteBookByAuthorRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("bookAuthor", "Book author should not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteBookByAuthorResponse response = service.deleteBookByAuthorResponse(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "bookAuthor");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "Book author should not be empty!");
    }

    @Test
    public void shouldDeleteBookWithBookAuthorFromElectronicLibrary() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(electronicLibrary.deleteBookByAuthor("aaa")).thenReturn(true);
        DeleteBookByAuthorRequest request = new DeleteBookByAuthorRequest("aaa");
        DeleteBookByAuthorResponse response = service.deleteBookByAuthorResponse(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isBookRemoved());
    }
}