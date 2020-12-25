package book_library.core.validators;

import book_library.Book;
import book_library.core.database.Database;
import book_library.core.database.InMemoryDataBaseImpl;
import book_library.core.requests.AddBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.validators.AddBookValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddBookValidatorTest {
    private InMemoryDataBaseImpl database = new InMemoryDataBaseImpl();
    private AddBookValidator validator = new AddBookValidator(database);

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
        assertEquals(errors.size(),1);
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
    public void shouldReturnErrorWhenSuchBookIsInDatabase() {
        Book book = new Book("Title", "Author");
        database.save(book);
        AddBookRequest request = new AddBookRequest("Title", "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1,errors.size());
        assertEquals("Title and author" , errors.get(0).getField());
        assertEquals("Such book already exists!", errors.get(0).getMessage());
    }

}