package bookLibrary.acceptanceTest;

import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.dataBase.OrmBookRepositoryImpl;
import bookLibrary.core.request.*;
import bookLibrary.core.response.*;
import bookLibrary.core.service.*;
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

        GetBookIdRequest getBookIdRequest = new GetBookIdRequest("Author", "Title");
        GetBookIdResponse getBookIdResponse = getBookIdService().execute(getBookIdRequest);

        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(getBookIdResponse.getBookId());
        DeleteBookResponse deleteBookResponse = getDeleteBookService().execute(deleteBookRequest);

        assertEquals(true, deleteBookResponse.isBookDeleted());
    }

    @Test
    public void addDeleteBookGetAllTitle() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse addBookResponse = getAddBookService().execute(request);

        GetBookIdRequest getBookIdRequest = new GetBookIdRequest("Author", "Title");
        GetBookIdResponse getBookIdResponse = getBookIdService().execute(getBookIdRequest);


        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(getBookIdResponse.getBookId());
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

    private OrmBookRepositoryImpl getOrmBookRepository() {
        return appContext.getBean(OrmBookRepositoryImpl.class);
    }

    private SearchBooksService getSearchBookService() {
        return appContext.getBean(SearchBooksService.class);
    }

    private GetBookIdService getBookIdService() { return appContext.getBean(GetBookIdService.class); }
}
