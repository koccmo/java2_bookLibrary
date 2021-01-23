package electronic_library.acceptance_tests;

import electronic_library.config.BookListConfiguration;
import electronic_library.core.requests.AddBookRequest;
import electronic_library.core.requests.GetAllBooksRequest;
import electronic_library.core.responses.GetAllBooksResponse;
import electronic_library.core.services.AddBookService;
import electronic_library.core.services.GetAllBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Profile("noJdbc")
public class AcceptanceTest_AddBook {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
    }

    @Test
    public void addBooksWithoutErrors() {
        AddBookRequest addBookRequestOne = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequestOne);
        AddBookRequest addBookRequestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal("11.00"), 2011);
        getAddBookService().execute(addBookRequestTwo);
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(2, response.getBooks().size());
    }

    @Test
    public void addBookWithErrorInBookTitle() {
        AddBookRequest addBookRequest = new AddBookRequest("", "aaa", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequest);
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(0, response.getBooks().size());
    }

    @Test
    public void addBookWithErrorInBookAuthor() {
        AddBookRequest addBookRequest = new AddBookRequest("aaa", "", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequest);
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(0, response.getBooks().size());
    }

    @Test
    public void addBookWithErrorInBookPrice() {
        AddBookRequest addBookRequest = new AddBookRequest("aaa", "aaa", new BigDecimal("0.00"), 2010);
        getAddBookService().execute(addBookRequest);
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(0, response.getBooks().size());
    }

    @Test
    public void addBookWithErrorInYearOfBookIssue() {
        AddBookRequest addBookRequest = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 0);
        getAddBookService().execute(addBookRequest);
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(0, response.getBooks().size());
    }
    private AddBookService getAddBookService() {
        return applicationContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {
        return applicationContext.getBean(GetAllBooksService.class);
    }
}

