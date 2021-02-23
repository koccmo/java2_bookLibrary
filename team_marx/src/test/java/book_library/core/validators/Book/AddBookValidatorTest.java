package book_library.core.validators.Book;

import book_library.core.database.Book.InMemoryBookRepositoryImpl;
import book_library.core.requests.Book.AddBookRequest;
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
public class AddBookValidatorTest {

    @Mock private InMemoryBookRepositoryImpl database;
    @InjectMocks private AddBookRequestValidator validator;

    @Test
    public void success() {
        AddBookRequest request = new AddBookRequest("Title", "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0 , errors.size());
    }

    @Test
    public void shouldReturnErrorWhenTitleIsNull() {
        AddBookRequest request = new AddBookRequest(null, "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1,errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals("Must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        AddBookRequest request = new AddBookRequest("", "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals("Must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenAuthorIsNull() {
        AddBookRequest request = new AddBookRequest("Title", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("author", errors.get(0).getField());
        assertEquals("Must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenAuthorIsEmpty() {
        AddBookRequest request = new AddBookRequest("Title", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1 , errors.size());
        assertEquals("author", errors.get(0).getField());
        assertEquals("Must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleOrAuthorIsNullOrIsEmpty() {
        AddBookRequest request = new AddBookRequest(null, "");
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleAndAuthorIsNull() {
        AddBookRequest request = new AddBookRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(2,errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals("Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenSuchBookIsInDatabase() {
        AddBookRequest request = new AddBookRequest("Title", "Author");

        List<CoreError> validationErrors = new ArrayList<>();
        validationErrors.add(new CoreError("Title and author", "Such book already exists!"));
        Mockito.when(database.hasTheSameBookInDatabase(any())).thenReturn(true);

        List<CoreError> errors = validator.validate(request);
        Mockito.verify(database).hasTheSameBookInDatabase(
                argThat(new BookMatcher("Title", "Author")));
        assertEquals(1,errors.size());
        assertEquals("Title and author" , errors.get(0).getField());
        assertEquals("Such book already exists!", errors.get(0).getMessage());
    }

}