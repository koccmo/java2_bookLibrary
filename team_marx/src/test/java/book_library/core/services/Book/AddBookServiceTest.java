package book_library.core.services.Book;

import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.AddBookRequestValidator;
import book_library.matchers.BookMatcher;
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
public class AddBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AddBookRequestValidator validator;
    @InjectMocks
    private AddBookService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddBookRequest request = new AddBookRequest(null, "Author");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("title", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("title", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());

        Mockito.verifyNoInteractions(bookRepository);
    }

    @Test

    public void shouldAddBookToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddBookRequest request = new AddBookRequest("Title", "Author");
        AddBookResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(bookRepository).save(
                argThat(new BookMatcher("Title", "Author")));
    }

}