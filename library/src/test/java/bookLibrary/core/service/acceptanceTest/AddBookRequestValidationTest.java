package bookLibrary.core.service.acceptanceTest;

import bookLibrary.dependency_injection.ApplicationContext;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.validators.AddBookValidator;
import bookLibrary.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AddBookRequestValidationTest {

    private ApplicationContext context = new DIApplicationContextBuilder().build("bookLibrary");

    @Test
    public void shouldReturnErrorWhenAuthorEmpty() {
        AddBookRequest request = new AddBookRequest("", "Title");
        List<CoreError> errors = getAddBookValidator().validate(request, getDataBase());
        assertEquals(1, errors.size());
        assertEquals("Author", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenTitleEmpty() {
        AddBookRequest request = new AddBookRequest("Author", "");
        List<CoreError> errors = getAddBookValidator().validate(request, getDataBase());
        assertEquals(1, errors.size());
        assertEquals("Title", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnResponseWithAddedBook() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse response = getAddBookService().execute(request);
        assertEquals("Author", response.getBook().getAuthor());
        assertEquals("Title", response.getBook().getTitle());
    }

    private AddBookValidator getAddBookValidator() {
        return context.getBean(AddBookValidator.class);
    }

    private AddBookService getAddBookService() {
        return context.getBean(AddBookService.class);
    }

    private DataBase getDataBase() {
        return context.getBean(DataBase.class);
    }
}
