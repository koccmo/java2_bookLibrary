package electronic_library.acceptance_tests.lesson_5;

import lesson_5.ApplicationContext;
import lesson_5.core.requests.AddBookRequest;
import lesson_5.core.requests.GetAllBooksRequest;
import lesson_5.core.responses.GetAllBooksResponse;
import lesson_5.core.services.AddBookService;
import lesson_5.core.services.GetAllBooksService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest_01 {

    private ApplicationContext appContext = new ApplicationContext();

    @Test
    public void shouldReturnCorrectBookList() {
        AddBookRequest addBookRequest1 = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("bbb", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(addBookRequest2);

        GetAllBooksResponse response = getAllBooksService().execute(new GetAllBooksRequest());
        assertEquals(response.getBooks().size(), 2);
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {
        return appContext.getBean(GetAllBooksService.class);
    }
}
