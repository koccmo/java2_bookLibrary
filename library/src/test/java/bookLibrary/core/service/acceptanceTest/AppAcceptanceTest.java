package bookLibrary.core.service.acceptanceTest;

import bookLibrary.dependency_injection.ApplicationContext;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.response.DeleteBookResponse;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import bookLibrary.core.response.SearchBooksResponse;
import bookLibrary.core.service.AddBookService;
import bookLibrary.core.service.DeleteBookService;
import bookLibrary.core.service.PrintAllBookTitleService;
import bookLibrary.core.service.SearchBooksService;
import bookLibrary.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppAcceptanceTest {

    ApplicationContext context = new DIApplicationContextBuilder().build("bookLibrary");

    @Test
    public void addDeleteBookReturnTrueBookDeleted() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse addBookResponse = getAddBookService().execute(request);
        String id = String.valueOf(addBookResponse.getBook().getId());

        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(id);
        DeleteBookResponse deleteBookResponse = getDeleteBookService().execute(deleteBookRequest);

        assertEquals(true, deleteBookResponse.isBookDeleted());
    }

    @Test
    public void addDeleteBookGetAllTitle() {
        AddBookRequest request = new AddBookRequest("Author", "Title");
        AddBookResponse addBookResponse = getAddBookService().execute(request);
        String id = String.valueOf(addBookResponse.getBook().getId());

        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(id);
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
        return context.getBean(AddBookService.class);
    }

    private DeleteBookService getDeleteBookService() {
        return context.getBean(DeleteBookService.class);
    }

    private PrintAllBookTitleService getPrintAllBookTitleService() {
        return context.getBean(PrintAllBookTitleService.class);
    }

    private SearchBooksService getSearchBookService() {
        return context.getBean(SearchBooksService.class);
    }
}
