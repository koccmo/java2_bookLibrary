package bookLibrary.core.service.acceptanceTest;

import bookLibrary.dependency_injection.ApplicationContext;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddBookRequestAcceptanceTest {

    private ApplicationContext context = new DIApplicationContextBuilder().build("bookLibrary");

    @Test
    public void shouldReturnCorrectBookList() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse response = getAddBookService().execute(request);
        assertEquals("Author", response.getBook().getAuthor());
        assertEquals("Title", response.getBook().getTitle());
    }

    private AddBookService getAddBookService() {
        return context.getBean(AddBookService.class);
    }


}
