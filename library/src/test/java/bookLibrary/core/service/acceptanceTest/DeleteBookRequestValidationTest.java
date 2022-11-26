package bookLibrary.core.service.acceptanceTest;

import bookLibrary.dependency_injection.ApplicationContext;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.validators.DeleteBookValidation;
import bookLibrary.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class DeleteBookRequestValidationTest {
    private ApplicationContext context = new DIApplicationContextBuilder().build("bookLibrary");

    @Test
    public void shouldReturnEmptyErrorsList() {
        AddBookRequest addBookRequest = new AddBookRequest("Author", "Title");
        getAddBookService().execute(addBookRequest);
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest("1");
        List<CoreError> errors = getDeleteBookValidation().validate(deleteBookRequest, getDataBase());
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenIdNotNumber() {
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest("a");
        List<CoreError> errors = getDeleteBookValidation().validate(deleteBookRequest, getDataBase());
        assertEquals(1, errors.size());
        assertEquals("Id", errors.get(0).getField());
        assertEquals("Id must has only Numbers", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenIdNotFillUp() {
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest("");
        List<CoreError> errors = getDeleteBookValidation().validate(deleteBookRequest, getDataBase());
        assertEquals(1, errors.size());
        assertEquals("ID", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenBookWithIdNotFound() {
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest("1");
        List<CoreError> errors = getDeleteBookValidation().validate(deleteBookRequest, getDataBase());

        assertEquals(1, errors.size());
        assertEquals("Id", errors.get(0).getField());
        assertEquals("Not found book with this ID!", errors.get(0).getDescription());
    }

    private DeleteBookValidation getDeleteBookValidation() {
        return context.getBean(DeleteBookValidation.class);
    }

    private DataBase getDataBase() {
       return context.getBean(DataBase.class);
    }

    private AddBookService getAddBookService() {
        return context.getBean(AddBookService.class);
    }




}
