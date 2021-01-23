package electronic_library.acceptance_tests.lesson_5;

import lesson_5.ApplicationContext;
import lesson_5.core.requests.AddBookRequest;
import lesson_5.core.requests.FindBooksRequest;
import lesson_5.core.requests.Ordering;
import lesson_5.core.requests.Paging;
import lesson_5.core.responses.FindBooksResponse;
import lesson_5.core.services.AddBookService;
import lesson_5.core.services.FindBooksService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest_02 {

    private ApplicationContext appContext = new ApplicationContext();

    @Test
    public void findBooks() {
        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("aaa", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        FindBooksRequest requestThree = new FindBooksRequest("aaa", null);
        FindBooksResponse response = getFindBooksService().execute(requestThree);

        assertEquals(response.getBooks().size(), 2);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(1).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "bbb");
    }

    @Test
    public void findBooksOrderingDescending() {
        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("aaa", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        Ordering ordering = new Ordering("author", "DESC");
        FindBooksRequest requestThree = new FindBooksRequest("aaa", null, ordering);
        FindBooksResponse response = getFindBooksService().execute(requestThree);

        assertEquals(response.getBooks().size(), 2);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "bbb");
        assertEquals(response.getBooks().get(1).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "aaa");
    }

    @Test
    public void findBooksOrderingAscending() {
        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("aaa", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        Ordering ordering = new Ordering("author", "ASC");
        FindBooksRequest requestThree = new FindBooksRequest("aaa", null, ordering);
        FindBooksResponse response = getFindBooksService().execute(requestThree);

        assertEquals(response.getBooks().size(), 2);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(1).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "bbb");
    }

    @Test
    public void findBooksOrderingPaging() {
        AddBookRequest requestOne = new AddBookRequest("aaa", "aaa", new BigDecimal(10.00),2001);
        getAddBookService().execute(requestOne);

        AddBookRequest requestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal(20.00),2002);
        getAddBookService().execute(requestTwo);

        Ordering ordering = new Ordering("author", "ASC");
        Paging paging = new Paging(1, 1);
        FindBooksRequest requestThree = new FindBooksRequest("aaa", null, ordering, paging);
        FindBooksResponse response = getFindBooksService().execute(requestThree);

        assertEquals(response.getBooks().size(), 1);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }
    private FindBooksService getFindBooksService() {
        return appContext.getBean(FindBooksService.class);
    }
}
