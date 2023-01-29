package bookLibrary.acceptanceTest;

import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.Ordering;
import bookLibrary.core.request.Paging;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.SearchBooksResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.SearchBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class SearchBooksRequestAcceptanceTest {
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
    @Before
    public void init() {
        AddBookRequest addBookRequest = new AddBookRequest("Author", "Title");
        AddBookRequest addBookRequest1 = new AddBookRequest("Author1", "Title");
        AddBookRequest addBookRequest2 = new AddBookRequest("Author", "Title1");
        getAddBookService().execute(addBookRequest);
        getAddBookService().execute(addBookRequest1);
        getAddBookService().execute(addBookRequest2);
    }

    @Test
    public void searchBook() {
        SearchBooksRequest request = new SearchBooksRequest("Author", "");
        SearchBooksResponse response = getSearchBookService().execute(request);

        assertEquals(2, response.getFoundedBookList().size());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title1", response.getFoundedBookList().get(1).getTitle());
        assertEquals("Author", response.getFoundedBookList().get(1).getAuthor());
    }

    @Test
    public void searchBookOrderingAscending() {
        Ordering ordering = new Ordering("Author", "ASCENDING");
        ordering.setFirstField("Author");
        ordering.setSecondField("Title");
        SearchBooksRequest request = new SearchBooksRequest("", "Title", ordering);
        SearchBooksResponse response = getSearchBookService().execute(request);

        assertEquals(2, response.getFoundedBookList().size());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(1).getTitle());
        assertEquals("Author1", response.getFoundedBookList().get(1).getAuthor());
    }

    @Test
    public void searchBookOrderingDescending() {
        Ordering ordering = new Ordering("Author", "DESCENDING");
        ordering.setFirstField("Author");
        ordering.setSecondField("Title");

        SearchBooksRequest searchBooksRequest = new SearchBooksRequest("", "Title", ordering);
        SearchBooksResponse response = getSearchBookService().execute(searchBooksRequest);

        assertEquals(2, response.getFoundedBookList().size());
        assertEquals("Author1", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
        assertEquals("Author", response.getFoundedBookList().get(1).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(1).getTitle());
    }

    @Test
    public void searchBookPagingPageNumberAndPageSizeOne() {
        Paging paging = new Paging(1, 1);
        SearchBooksRequest searchBooksRequest = new SearchBooksRequest("", "Title", null, paging);
        SearchBooksResponse searchBooksResponse = getSearchBookService().execute(searchBooksRequest);

        assertEquals(1, searchBooksResponse.getFoundedBookList().size());
        assertEquals("Author", searchBooksResponse.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", searchBooksResponse.getFoundedBookList().get(0).getTitle());
    }

    @Test
    public void searchBookPagingPageNumberTwoPageSizeOne() {
        Paging paging = new Paging(2, 1);
        SearchBooksRequest request = new SearchBooksRequest("Author", "", null, paging);
        SearchBooksResponse response = getSearchBookService().execute(request);

        assertEquals(1, response.getFoundedBookList().size());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title1", response.getFoundedBookList().get(0).getTitle());
    }

    private SearchBooksService getSearchBookService() {
        return appContext.getBean(SearchBooksService.class);
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }
}
