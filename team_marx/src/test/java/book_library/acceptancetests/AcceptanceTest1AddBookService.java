package book_library.acceptancetests;

import book_library.ApplicationContext;
import book_library.core.requests.AddBookRequest;
import book_library.core.requests.GetAllBooksRequest;
import book_library.core.responses.GetAllBooksResponse;
import book_library.core.services.AddBookService;
import book_library.core.services.GetAllBooksService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest1AddBookService {

    private ApplicationContext appContext = new ApplicationContext();

    @Test
    public void shouldReturnCorrectBookList() {
        AddBookRequest addBookRequest1 = new AddBookRequest("TitleA", "AuthorA");
        getAddBookService().execute(addBookRequest1);

        AddBookRequest addBookRequest2 = new AddBookRequest("TitleB", "AuthorA");
        getAddBookService().execute(addBookRequest2);

        GetAllBooksRequest getAllBooksRequest1 = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService().execute(getAllBooksRequest1);
        assertEquals(2, response.getBooks().size());
        assertEquals("TitleA", response.getBooks().get(0).getTitle());
        assertEquals("AuthorA", response.getBooks().get(0).getAuthor());
        assertEquals("TitleB", response.getBooks().get(1).getTitle());
        assertEquals("AuthorA", response.getBooks().get(1).getAuthor());
    }

    private AddBookService getAddBookService() {
        return appContext.getBean(AddBookService.class);
    }

    private GetAllBooksService getAllBooksService() {
        return appContext.getBean(GetAllBooksService.class);
    }
}
