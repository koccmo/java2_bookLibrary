package bookLibrary.acceptanceTest;

import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.PrintAllBookTitleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class PrintAllBookTitleServiceAcceptanceTest {
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
    public void shouldReturnBookTitlesList() {
        AddBookRequest addBookRequest = new AddBookRequest("Author", "Title");
        AddBookRequest addBookRequest1 = new AddBookRequest("Author", "Garden");
        getAddBookService().execute(addBookRequest);
        getAddBookService().execute(addBookRequest1);

        PrintAllBooksTitleRequest printAllBooksTitleRequest = new PrintAllBooksTitleRequest();
        PrintAllBooksTitleResponse response = getPrintAllBookTitleService().execute(printAllBooksTitleRequest);

        assertEquals(2, response.getBookList().size());
        assertEquals("Garden", response.getBookList().get(0));
        assertEquals("Title", response.getBookList().get(1));
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private PrintAllBookTitleService getPrintAllBookTitleService() {
        return appContext.getBean(PrintAllBookTitleService.class);
    }
}
