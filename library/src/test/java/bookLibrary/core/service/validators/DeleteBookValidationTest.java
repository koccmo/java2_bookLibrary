package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.dataBase.InMemoryDatabaseImpl;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeleteBookValidationTest {
    private DeleteBookValidation validation = new DeleteBookValidation();
    private DataBase dataBase = new InMemoryDatabaseImpl();

    @Test
    public void shouldReturnErrorIdEnteredNotNumbers() {
        DeleteBookRequest request = new DeleteBookRequest("s");
        List<CoreError> errors = validation.validate(request, dataBase);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("Id", errors.get(0).getField());
        assertEquals("Id must has only Numbers", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenIdNull() {
        DeleteBookRequest request = new DeleteBookRequest(null);
        List<CoreError> errors = validation.validate(request, dataBase);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("ID", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenNoBookWithThisId() {
        DeleteBookRequest request = new DeleteBookRequest("1");
        List<CoreError> errors = validation.validate(request, dataBase);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("Id", errors.get(0).getField());
        assertEquals("Not found book with this ID!", errors.get(0).getDescription());
    }

}