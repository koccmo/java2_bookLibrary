package bookLibrary.acceptanceTest;


import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.request.GetBookIdRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.GetBookIdResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.GetBookIdService;
import bookLibrary.core.service.validators.DeleteBookValidation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class DeleteBookRequestValidationTest {
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
    @Test
    public void shouldReturnEmptyErrorsList() {
        AddBookRequest addBookRequest = new AddBookRequest("Author", "Title");
        getAddBookService().execute(addBookRequest);

        GetBookIdRequest getBookIdRequest = new GetBookIdRequest(addBookRequest.getAuthor(), addBookRequest.getTitle());
        GetBookIdResponse getBookIdResponse = getBookIdService().execute(getBookIdRequest);

        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(getBookIdResponse.getBookId());
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
        return appContext.getBean(DeleteBookValidation.class);
    }

    private DataBase getDataBase() {
       return appContext.getBean(DataBase.class);
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private GetBookIdService getBookIdService() { return appContext.getBean(GetBookIdService.class); }


}
