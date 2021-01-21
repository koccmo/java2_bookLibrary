package electronic_library.acceptance_tests;

import electronic_library.config.BookListConfiguration;
import electronic_library.core.requests.AddBookRequest;

import electronic_library.core.requests.DeleteBookByTitleRequest;
import electronic_library.core.requests.GetAllBooksRequest;
import electronic_library.core.responses.DeleteBookByTitleResponse;
import electronic_library.core.responses.GetAllBooksResponse;
import electronic_library.core.services.AddBookService;

import electronic_library.core.services.DeleteBookByTitleService;
import electronic_library.core.services.GetAllBooksService;
import lesson_5.core.requests.FindBooksRequest;
import lesson_5.core.responses.FindBooksResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Profile("noJdbc")
public class AcceptanceTest_DeleteBookByBookTitle {

    private AnnotationConfigApplicationContext applicationContext;


    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        AddBookRequest addBookRequestOne = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequestOne);
        AddBookRequest addBookRequestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal("11.00"), 2011);
        getAddBookService().execute(addBookRequestTwo);
    }

    @Test
    public void deleteByBookTitleWithoutErrors() {

        DeleteBookByTitleRequest deleteRequest = new DeleteBookByTitleRequest("aaa");
        getDeleteBookByTitleService().deleteBookByTitleResponse(deleteRequest);

        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(request);
        assertEquals(1, response.getBooks().size());

        assertEquals(response.getBooks().get(0).getBookTitle(), "bbb");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "bbb");

    }

    private AddBookService getAddBookService() {
        return applicationContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {
        return applicationContext.getBean(GetAllBooksService.class);
    }

    private DeleteBookByTitleService getDeleteBookByTitleService() { return applicationContext.getBean(DeleteBookByTitleService.class); }
}
