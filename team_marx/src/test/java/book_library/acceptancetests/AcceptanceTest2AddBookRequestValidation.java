package book_library.acceptancetests;

import book_library.dependency_injection.ApplicationContext;
import book_library.core.requests.AddBookRequest;
import book_library.core.responses.AddBookResponse;
import book_library.core.services.AddBookService;
import book_library.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest2AddBookRequestValidation {

    private ApplicationContext appContext = new DIApplicationContextBuilder().build("book_library");

    @Test
    public void shouldPasseValidation() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title", "Author");
        AddBookResponse response = getAddBookService().execute(addBookRequest1);

        assertEquals(null, response.getErrors());
    }

    @Test
    public void shouldReturnErrorNotValidTitle() {
        AddBookRequest addBookRequest1 = new AddBookRequest(null, "Author");
        AddBookResponse response = getAddBookService().execute(addBookRequest1);

        assertEquals(1, response.getErrors().size());
        assertEquals("title", response.getErrors().get(0).getField());
        assertEquals("Must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorNotValidAuthor() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title", null);
        AddBookResponse response = getAddBookService().execute(addBookRequest1);

        assertEquals(1, response.getErrors().size());
        assertEquals("author", response.getErrors().get(0).getField());
        assertEquals("Must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorSuchBookInDatabase() {
        AddBookRequest addBookRequest1 = new AddBookRequest("Title", "Author");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("Title", "Author");
        AddBookResponse response = getAddBookService().execute(addBookRequest2);

        assertEquals(1, response.getErrors().size());
        assertEquals("Title and author", response.getErrors().get(0).getField());
        assertEquals("Such book already exists!", response.getErrors().get(0).getMessage());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }
}
