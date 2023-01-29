package bookLibrary.acceptanceTest;

import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.request.*;
import bookLibrary.core.response.GetBookIdResponse;
import bookLibrary.core.response.SearchReaderResponse;
import bookLibrary.core.response.TakeBookResponse;
import bookLibrary.core.service.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TakeBookAcceptanceTest {
    private ApplicationContext context;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDataBaseCleaner().clean();
    }

    @Before
    public void init() {
        AddBookRequest addBookRequest = new AddBookRequest("A", "B");
        getAddBookService().execute(addBookRequest);

        ReaderRegisteringRequest readerRegisteringRequest = new ReaderRegisteringRequest(
                "Q", "W", "1");
        getReaderRegisteringService().execute(readerRegisteringRequest);
    }

    private DatabaseCleaner getDataBaseCleaner() { return context.getBean(DatabaseCleaner.class);}
    private AddBookService getAddBookService() { return context.getBean(AddBookService.class);}
    private ReaderRegisteringService getReaderRegisteringService() { return context.getBean(ReaderRegisteringService.class);}
    private GetBookIdService getBookIdService() { return context.getBean(GetBookIdService.class);}
    private ReaderSearchService readerSearchService() { return context.getBean(ReaderSearchService.class);}
    private TakeBookService takeBookService() { return context.getBean(TakeBookService.class);}

    @Test
    public void takeBookFromLibrary() {
        GetBookIdRequest getBookIdRequest = new GetBookIdRequest("A", "B");
        GetBookIdResponse getBookIdResponse = getBookIdService().execute(getBookIdRequest);
        String bookId = getBookIdResponse.getBookId();

        SearchReaderRequest searchReaderRequest = new SearchReaderRequest("Q", "W");
        SearchReaderResponse searchReaderResponse = readerSearchService().execute(searchReaderRequest);
        Long readerId = searchReaderResponse.getReaderList().get(0).getId();

        TakeBookRequest takeBookRequest = new TakeBookRequest(String.valueOf(readerId), bookId, new Date());
        TakeBookResponse takeBookResponse = takeBookService().execute(takeBookRequest);

        assertTrue(takeBookResponse.isBookToken());
    }
}

