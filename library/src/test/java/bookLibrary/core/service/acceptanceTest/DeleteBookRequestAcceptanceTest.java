package bookLibrary.core.service.acceptanceTest;

import bookLibrary.dependency_injection.ApplicationContext;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.DeleteBookResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.DeleteBookService;
import bookLibrary.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DeleteBookRequestAcceptanceTest {
    private ApplicationContext context = new DIApplicationContextBuilder().build("bookLibrary");

    @Test
    public void shouldReturnResponseWithErrorWhenIdEmpty() {
        DeleteBookRequest request = new DeleteBookRequest("");
        DeleteBookResponse response = getDeleteBookService().execute(request);

        assertEquals(1, response.getErrors().size());
        assertEquals("ID", response.getErrors().get(0).getField());
        assertEquals("Must be fill UP!", response.getErrors().get(0).getDescription());
    }

    @Test
    public void shouldReturnTrueWhenBookFindAndDeleted() {
        AddBookRequest addBookRequest = new AddBookRequest("Author", "Title");
        getAddBookService().execute(addBookRequest);

        DeleteBookRequest request = new DeleteBookRequest("1");
        DeleteBookResponse deleteBookResponse = getDeleteBookService().execute(request);

        assertEquals(true, deleteBookResponse.isBookDeleted());
    }


    private DeleteBookService getDeleteBookService() {
        return context.getBean(DeleteBookService.class);
    }

    private AddBookService getAddBookService() {
        return context.getBean(AddBookService.class);
    }
}
