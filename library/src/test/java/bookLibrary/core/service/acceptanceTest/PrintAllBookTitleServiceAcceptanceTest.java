package bookLibrary.core.service.acceptanceTest;

import bookLibrary.dependency_injection.ApplicationContext;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.PrintAllBookTitleService;
import bookLibrary.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrintAllBookTitleServiceAcceptanceTest {
    private ApplicationContext context = new DIApplicationContextBuilder().build("bookLibrary");

    @Test
    public void shouldReturnBookTitlesList() {
        AddBookRequest addBookRequest = new AddBookRequest("Author", "Title");
        AddBookRequest addBookRequest1 = new AddBookRequest("Author", "Garden");
        getAddBookService().execute(addBookRequest);
        getAddBookService().execute(addBookRequest1);

        PrintAllBooksTitleRequest printAllBooksTitleRequest = new PrintAllBooksTitleRequest();
        PrintAllBooksTitleResponse response = getPrintAllBookTitleService().execute(printAllBooksTitleRequest);

        assertEquals(2, response.getBookList().size());
        assertEquals("Title", response.getBookList().get(0));
        assertEquals("Garden", response.getBookList().get(1));
    }

    private AddBookService getAddBookService() {
        return context.getBean(AddBookService.class);
    }

    private PrintAllBookTitleService getPrintAllBookTitleService() {
        return context.getBean(PrintAllBookTitleService.class);
    }
}
