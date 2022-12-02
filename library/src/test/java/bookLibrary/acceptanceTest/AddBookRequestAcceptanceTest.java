package bookLibrary.acceptanceTest;

import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.service.AddBookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.testng.AssertJUnit.assertEquals;

public class AddBookRequestAcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
    }

    @Test
    public void shouldReturnCorrectBookList() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse response = getAddBookService().execute(request);
        assertEquals("Author", response.getBook().getAuthor());
        assertEquals("Title", response.getBook().getTitle());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }


}
