package bookLibrary.acceptanceTest;

import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.dataBase.JdbcDatabaseImpl;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.response.DeleteBookResponse;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import bookLibrary.core.response.SearchBooksResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.DeleteBookService;
import bookLibrary.core.service.PrintAllBookTitleService;
import bookLibrary.core.service.SearchBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppAcceptanceTest {

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
    public void addDeleteBookReturnTrueBookDeleted() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse addBookResponse = getAddBookService().execute(request);

        Long id = getJdbcDatabaseImpl().getBookId(request.getAuthor(), request.getTitle());
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(String.valueOf(id));
        DeleteBookResponse deleteBookResponse = getDeleteBookService().execute(deleteBookRequest);

        assertEquals(true, deleteBookResponse.isBookDeleted());
    }

    @Test
    public void addDeleteBookGetAllTitle() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse addBookResponse = getAddBookService().execute(request);
        Long id = getJdbcDatabaseImpl().getBookId(request.getAuthor(), request.getTitle());

        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(String.valueOf(id));
        DeleteBookResponse deleteBookResponse = getDeleteBookService().execute(deleteBookRequest);

        PrintAllBooksTitleRequest printAllBooksTitleRequest = new PrintAllBooksTitleRequest();
        PrintAllBooksTitleResponse allBooksTitleResponse = getPrintAllBookTitleService().execute(printAllBooksTitleRequest);

        assertTrue(allBooksTitleResponse.getBookList().isEmpty());
    }

    @Test
    public void addBookAndSearchByAuthor() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        getAddBookService().execute(request);

        SearchBooksRequest searchBooksRequest = new SearchBooksRequest("Author", "");
        SearchBooksResponse response = getSearchBookService().execute(searchBooksRequest);

        assertEquals(1,response.getFoundedBookList().size());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private DeleteBookService getDeleteBookService() {
        return appContext.getBean(DeleteBookService.class);
    }

    private PrintAllBookTitleService getPrintAllBookTitleService() {
        return appContext.getBean(PrintAllBookTitleService.class);
    }

    private JdbcDatabaseImpl getJdbcDatabaseImpl() {
        return appContext.getBean(JdbcDatabaseImpl.class);
    }

    private SearchBooksService getSearchBookService() {
        return appContext.getBean(SearchBooksService.class);
    }
}
