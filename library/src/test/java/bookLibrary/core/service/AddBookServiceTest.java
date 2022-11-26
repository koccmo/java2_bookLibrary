package bookLibrary.core.service;

import bookLibrary.Book;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.service.matchers.BookMather;
import bookLibrary.core.service.validators.AddBookValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceTest {
    @Mock private DataBase database;
    @Mock
    private AddBookValidator validator;
    @InjectMocks
    private AddBookService service;


//    @Before
//    public void init() {
//        database = Mockito.mock(DataBase.class);
//        validator = Mockito.mock(AddBookValidator.class);
//        service = new AddBookService(database, validator);
//    }


    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddBookRequest notValidRequest = new AddBookRequest(null, "Title");
        when(validator.validate(notValidRequest, database))
                .thenReturn(List.of(new CoreError("Author", "Must be Fill UP!")));
        AddBookResponse response = service.execute(notValidRequest);
        assertTrue(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithErrorsFromValidator() {
        AddBookRequest request = new AddBookRequest(null, "Title");
        when(validator.validate(request, database))
                .thenReturn(List.of(new CoreError("Author", "Must be Fill UP!")));
        AddBookResponse response = service.execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Author", response.getErrors().get(0).getField());
        assertEquals("Must be Fill UP!", response.getErrors().get(0).getDescription());
    }

    @Test
    public void testCaptor() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        when(validator.validate(request, database)).thenReturn(List.of());
        service.execute(request);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(database).addBook(captor.capture());
        Book argument = captor.getValue();
        assertEquals(argument, new Book("Author", "Title"));
    }

    @Test
    public void notInvokeDataBaseThenRequestValidationFail() {
        AddBookRequest request = new AddBookRequest(null, "Title");
        when(validator.validate(request, database))
                .thenReturn(List.of(new CoreError("Author", "Must be Fill UP!")));
        service.execute(request);
        verifyNoInteractions(database);
    }

    @Test
    public void shouldResponseNoErrorsThenRequestValid() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        when(validator.validate(request, database)).thenReturn(List.of());
        AddBookResponse response = service.execute(request);
        assertFalse(response.hasError());
    }
    @Test
    public void shouldAddBookToDataBaseWhenRequestValid() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        when(validator.validate(request,database)).thenReturn(List.of());
        AddBookResponse response = service.execute(request);
        assertNotNull(response);
        assertEquals(request.getAuthor(), response.getBook().getAuthor());
        assertEquals(request.getTitle(), response.getBook().getTitle());
    }

    @Test
    public void shouldAddBookToDataBaseWhenRequestIsValid() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        when(validator.validate(request, database)).thenReturn(List.of());
        service.execute(request);
        verify(database).addBook(argThat(new BookMather("Author", "Title")));
    }

}