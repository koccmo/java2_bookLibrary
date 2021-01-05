package book_library.core.services;

import book_library.core.database.Database;
import book_library.core.requests.RemoveBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.RemoveBookResponse;
import book_library.core.validators.RemoveBookRequestValidator;
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

@RunWith(MockitoJUnitRunner.class)
public class RemoveBookServiceTest {

    @Mock
    private Database database;
    @Mock
    private RemoveBookRequestValidator validator;
    @InjectMocks
    private RemoveBookService service;

    @Test
    public void shouldReturnErrorWhenBookIdNotProvided() {
        RemoveBookRequest request = new RemoveBookRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("bookId", "Must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("bookId", response.getErrors().get(0).getField());
        assertEquals("Must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldDeleteBookWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteById(1L)).thenReturn(true);
        RemoveBookRequest request = new RemoveBookRequest(1L);
        RemoveBookResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isBookRemoved());
    }

}