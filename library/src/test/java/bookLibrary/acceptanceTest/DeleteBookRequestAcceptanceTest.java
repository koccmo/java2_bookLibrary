package bookLibrary.acceptanceTest;


import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.DeleteBookResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.DeleteBookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;


public class DeleteBookRequestAcceptanceTest {
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
    }

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
        return appContext.getBean(DeleteBookService.class);
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }
}
