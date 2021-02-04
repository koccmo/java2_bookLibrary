package electronic_library.core.services;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.requests.book.DeleteBookByTitleRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.book.DeleteBookByTitleResponse;
import electronic_library.core.services.book.DeleteBookByTitleService;
import electronic_library.core.services.book.validators.DeleteBookByTitleValidator;
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
public class DeleteBookByTitleServiceTest {

    @Mock
    private ElectronicLibraryRepository electronicLibrary;

    @Mock
    private DeleteBookByTitleValidator validator;

    @InjectMocks
    private DeleteBookByTitleService service;

    @Test
    public void shouldReturnErrorWhenBookTitleNotProvided() {
        DeleteBookByTitleRequest request = new DeleteBookByTitleRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("bookTitle", "Book title should not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteBookByTitleResponse response = service.deleteBookByTitleResponse(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "bookTitle");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "Book title should not be empty!");
    }

    @Test
    public void shouldDeleteBookWithBookTitleFromElectronicLibrary() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(electronicLibrary.deleteBookByTitle("aaa")).thenReturn(true);
        DeleteBookByTitleRequest request = new DeleteBookByTitleRequest("aaa");
        DeleteBookByTitleResponse response = service.deleteBookByTitleResponse(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isBookRemoved());
    }
}