package electronic_library.acceptance_tests;

import electronic_library.DatabaseCleaner;
import electronic_library.config.BookListConfiguration;
import electronic_library.core.requests.book.AddBookRequest;
import electronic_library.core.requests.book.DeleteBookByAuthorRequest;
import electronic_library.core.requests.book.GetAllBooksRequest;
import electronic_library.core.responses.book.GetAllBooksResponse;
import electronic_library.core.services.book.AddBookService;
import electronic_library.core.services.book.DeleteBookByAuthorService;
import electronic_library.core.services.book.GetAllBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Profile("orm")
public class AcceptanceTest_DeleteBookByBookAuthor {

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
    public void deleteByBookAuthorWithoutErrors() {

        DeleteBookByAuthorRequest deleteRequest = new DeleteBookByAuthorRequest("aaa");
        getDeleteBookByAuthorService().deleteBookByAuthorResponse(deleteRequest);

        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(1, response.getBooks().size());

        assertEquals(response.getBooks().get(0).getBookTitle(), "bbb");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "bbb");

    }

    @Test
    public void deleteByBookAuthorWithError() {

        DeleteBookByAuthorRequest deleteRequest = new DeleteBookByAuthorRequest("");
        getDeleteBookByAuthorService().deleteBookByAuthorResponse(deleteRequest);

        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(2, response.getBooks().size());

        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(1).getBookTitle(), "bbb");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "bbb");
    }

    private AddBookService getAddBookService() {
        return applicationContext.getBean(AddBookService.class);
    }
    private GetAllBooksService getAllBooksService() {
        return applicationContext.getBean(GetAllBooksService.class);
    }
    private DeleteBookByAuthorService getDeleteBookByAuthorService() { return applicationContext.getBean(DeleteBookByAuthorService.class); }
    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }
}

