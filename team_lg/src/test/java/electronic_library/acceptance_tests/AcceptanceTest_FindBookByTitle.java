package electronic_library.acceptance_tests;

import electronic_library.DatabaseCleaner;
import electronic_library.config.BookListConfiguration;
import electronic_library.core.requests.book.AddBookRequest;
import electronic_library.core.requests.book.FindBooksRequest;
import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.responses.book.FindBooksResponse;
import electronic_library.core.services.book.AddBookService;
import electronic_library.core.services.book.FindBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Profile("orm")
public class AcceptanceTest_FindBookByTitle {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDatabaseCleaner().clean();
        AddBookRequest addBookRequestOne = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequestOne);
        AddBookRequest addBookRequestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal("11.00"), 2011);
        getAddBookService().execute(addBookRequestTwo);
    }

    @Test
    public void findBooksByTitleWithoutErrors() {

        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("aaa", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        FindBooksRequest request = new FindBooksRequest("aaa",null);
        FindBooksResponse response = getFindBooksService().execute(request);

        assertEquals(response.getBooks().size(), 3);

        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(1).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(2).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(2).getBookAuthor(), "bbb");
    }


    @Test
    public void findBooksByTitleOrderingByAuthorDescending() {

        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("aaa", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        Ordering ordering = new Ordering("author", "DESC");
        FindBooksRequest request = new FindBooksRequest("aaa", null, ordering);
        FindBooksResponse response = getFindBooksService().execute(request);

        assertEquals(response.getBooks().size(), 3);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "bbb");
        assertEquals(response.getBooks().get(1).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(2).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(2).getBookAuthor(), "aaa");
    }

    @Test
    public void findBooksByTitleOrderingByAuthorAscending() {

        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("aaa", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        Ordering ordering = new Ordering("author", "ASC");
        FindBooksRequest request = new FindBooksRequest("aaa", null, ordering);
        FindBooksResponse response = getFindBooksService().execute(request);

        assertEquals(response.getBooks().size(), 3);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(1).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(2).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(2).getBookAuthor(), "bbb");
    }

    @Test
    public void findBooksByTitleAscendingByAuthorWithPaging() {

        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        Ordering ordering = new Ordering("author", "ASC");
        Paging paging = new Paging(1, 1);
        FindBooksRequest request = new FindBooksRequest("aaa", null, ordering, paging);
        FindBooksResponse response = getFindBooksService().execute(request);

        assertEquals(response.getBooks().size(), 1);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
    }
    private AddBookService getAddBookService() { return applicationContext.getBean(AddBookService.class); }
    private FindBooksService getFindBooksService() { return applicationContext.getBean(FindBooksService.class); }
    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }
}
