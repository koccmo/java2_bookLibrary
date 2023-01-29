package bookLibrary.acceptanceTest;

import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.request.ReaderRegisteringRequest;
import bookLibrary.core.request.SearchReaderRequest;
import bookLibrary.core.response.ReaderRegisteringResponse;
import bookLibrary.core.response.SearchReaderResponse;
import bookLibrary.core.service.ReaderRegisteringService;
import bookLibrary.core.service.ReaderSearchService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class ReaderSearchAcceptanceTest {
    private ApplicationContext context;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDataBaseCleaner().clean();
    }
    @Test
    public void registerAndSearchReader() {
        ReaderRegisteringRequest registeringRequest = new ReaderRegisteringRequest("A", "B", "1");
        ReaderRegisteringResponse registeringResponse = getReaderRegisteringService().execute(registeringRequest);

        SearchReaderRequest searchReaderRequest = new SearchReaderRequest("A", "");
        SearchReaderResponse searchReaderResponse = getSearchReaderService().execute(searchReaderRequest);

        assertFalse(searchReaderResponse.getReaderList().isEmpty());
        assertFalse(searchReaderResponse.hasError());
        assertEquals(1, searchReaderResponse.getReaderList().size());
        assertEquals("A", searchReaderResponse.getReaderList().get(0).getFirstName());
        assertEquals("B", searchReaderResponse.getReaderList().get(0).getLastName());
        assertEquals(Long.valueOf(1L), searchReaderResponse.getReaderList().get(0).getPersonal_code());
    }
    public ReaderSearchService getSearchReaderService() {return context.getBean(ReaderSearchService.class);}
    public ReaderRegisteringService getReaderRegisteringService() { return context.getBean(ReaderRegisteringService.class);}
    public DatabaseCleaner getDataBaseCleaner() { return context.getBean(DatabaseCleaner.class); }
}
