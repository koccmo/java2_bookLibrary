package bookLibrary.core.service.validators;

import bookLibrary.core.domain.Book;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.dataBase.InMemoryDatabaseImpl;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AddBookValidatorTest {
    DataBase dataBase = new InMemoryDatabaseImpl();

    @Test
    public void allDataIsValid() {
        AddBookRequest addBookRequest = new AddBookRequest("James G", "Garden");
        AddBookValidator addBookValidator = new AddBookValidator();
        List<CoreError> coreErrors = addBookValidator.validate(addBookRequest, dataBase);
        assertTrue(coreErrors.isEmpty());
    }

    @Test
    public void authorNotValid() {
        AddBookRequest addBookRequest = new AddBookRequest("", "Hope");
        AddBookValidator addBookValidator = new AddBookValidator();
        List<CoreError> coreErrors = addBookValidator.validate(addBookRequest, dataBase);
        CoreError coreError = new CoreError("Author", "Must be fill UP!");
        assertEquals(1, coreErrors.size());
        assertEquals(coreErrors.get(0), coreError);
    }

    @Test
    public void titleNotValid() {
        AddBookRequest addBookRequest = new AddBookRequest("A James", "");
        AddBookValidator addBookValidator = new AddBookValidator();
        List<CoreError> coreErrors = addBookValidator.validate(addBookRequest, dataBase);
        CoreError coreError = new CoreError("Title", "Must be fill UP!");
        assertEquals(1, coreErrors.size());
        assertEquals(coreErrors.get(0), coreError);
    }

    @Test
    public void authorAndTitleNotValid() {
        AddBookRequest addBookRequest = new AddBookRequest("", "");
        AddBookValidator addBookValidator = new AddBookValidator();
        List<CoreError> coreErrors = addBookValidator.validate(addBookRequest, dataBase);
        CoreError authorCoreError = new CoreError("Author", "Must be fill UP!");
        CoreError titleCoreError = new CoreError("Title", "Must be fill UP!");
        assertEquals(2, coreErrors.size());
        assertEquals(coreErrors.get(0), authorCoreError);
        assertEquals(coreErrors.get(1), titleCoreError);

    }

    @Test
    public void bookAlreadyInLibrary() {
        Book book = new Book("Adam", "Hope");
        dataBase.addBook(book);
        AddBookRequest request = new AddBookRequest("Adam", "Hope");
        AddBookValidator validator = new AddBookValidator();
        List<CoreError> coreErrors = validator.validate(request, dataBase);
        CoreError coreError = new CoreError("Book", "Can`t add to library, it already in!");
        assertFalse(coreErrors.isEmpty());
        assertEquals(coreErrors.get(0), coreError);
    }

}