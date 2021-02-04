package electronic_library.acceptance_tests;

import electronic_library.DatabaseCleaner;
import electronic_library.config.BookListConfiguration;
import electronic_library.core.requests.book.AddBookRequest;
import electronic_library.core.requests.book.GetAllBooksRequest;
import electronic_library.core.responses.book.GetAllBooksResponse;
import electronic_library.core.services.book.AddBookService;
import electronic_library.core.services.book.GetAllBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Profile("orm")
public class AcceptanceTest_GetAllBooks {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDatabaseCleaner().clean();
        AddBookRequest addBookRequestOne = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequestOne);
        AddBookRequest addBookRequestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal("11.00"), 2011);
        getAddBookService().execute(addBookRequestTwo);
        AddBookRequest addBookRequestThree = new AddBookRequest("ccc", "ccc", new BigDecimal("12.00"), 2012);
        getAddBookService().execute(addBookRequestOne);
        AddBookRequest addBookRequestFour = new AddBookRequest("ddd", "ddd", new BigDecimal("13.00"), 2013);
        getAddBookService().execute(addBookRequestTwo);
    }

    @Test
    public void bookListWithoutErrors() {
        GetAllBooksResponse response = getAllBooksService().execute(new GetAllBooksRequest());
        assertEquals(response.getBooks().size(), 4);
    }

    private AddBookService getAddBookService() {
        return applicationContext.getBean(AddBookService.class);
    }
    private GetAllBooksService getAllBooksService() {
        return applicationContext.getBean(GetAllBooksService.class);
    }
    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }
}
