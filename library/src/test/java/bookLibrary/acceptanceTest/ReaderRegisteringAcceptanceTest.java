package bookLibrary.acceptanceTest;

import bookLibrary.DatabaseCleaner;
import bookLibrary.config.BookListConfiguration;
import bookLibrary.core.request.ReaderRegisteringRequest;
import bookLibrary.core.response.ReaderRegisteringResponse;
import bookLibrary.core.service.ReaderRegisteringService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class ReaderRegisteringAcceptanceTest {
    private ApplicationContext context;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDataBaseCleaner();
    }

    @Test
    public void saveAndFindBuyIDReader() {
        ReaderRegisteringRequest request = new ReaderRegisteringRequest("A", "B", "1");
        ReaderRegisteringResponse response = getReaderRegisterService().execute(request);
        assertTrue(response.isReaderRegistered());
    }

    private DatabaseCleaner getDataBaseCleaner() {
       return context.getBean(DatabaseCleaner.class);
    }



    private ReaderRegisteringService getReaderRegisterService() { return context.getBean(ReaderRegisteringService.class); }

}
