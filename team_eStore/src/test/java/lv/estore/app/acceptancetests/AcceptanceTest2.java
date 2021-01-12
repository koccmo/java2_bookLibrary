package lv.estore.app.acceptancetests;

import lv.estore.app.config.EStoreConfiguration;
import lv.estore.app.core.request.AddRequest;
import lv.estore.app.core.request.Ordering;
import lv.estore.app.core.request.Paging;
import lv.estore.app.core.request.SearchRequest;
import lv.estore.app.core.responses.SearchResponse;
import lv.estore.app.core.services.AddService;
import lv.estore.app.core.services.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest2 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(EStoreConfiguration.class);
    }

    @Test
    public void searchProductsByName() {
        AddRequest request1 = new AddRequest("name", "description", "1.0");
        getAddService().execute(request1);

        AddRequest request2 = new AddRequest("name", "description2", "2.0");
        getAddService().execute(request2);

        SearchRequest request3 = new SearchRequest("name", null);
        SearchResponse response = getSearchService().execute(request3);

        assertEquals(2, response.getProducts().size());
        assertEquals("name", response.getProducts().get(0).getName());
        assertEquals("description", response.getProducts().get(0).getDescription());
        assertEquals("name", response.getProducts().get(1).getName());
        assertEquals("description2", response.getProducts().get(1).getDescription());
    }

    @Test
    public void searchProductsByName_OrderingByPrice_Descending() {
        AddRequest request1 = new AddRequest("name", "description1", "1.0");
        getAddService().execute(request1);

        AddRequest request2 = new AddRequest("name", "description2", "2.0");
        getAddService().execute(request2);

        Ordering ordering = new Ordering("price", "DESCENDING");
        SearchRequest request3 = new SearchRequest("name", null, ordering);
        SearchResponse response = getSearchService().execute(request3);

        assertEquals(2, response.getProducts().size());
        assertEquals("name", response.getProducts().get(0).getName());
        assertEquals("description2", response.getProducts().get(0).getDescription());
        assertEquals("2.00", response.getProducts().get(0).getPrice().toPlainString());
        assertEquals("name", response.getProducts().get(1).getName());
        assertEquals("description1", response.getProducts().get(1).getDescription());
        assertEquals("1.00", response.getProducts().get(1).getPrice().toPlainString());
    }

    @Test
    public void searchProductsByPrice_OrderingByName_Ascending() {
        AddRequest request1 = new AddRequest("name2", "description2", "1.0");
        getAddService().execute(request1);

        AddRequest request2 = new AddRequest("name1", "description1", "1.0");
        getAddService().execute(request2);

        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchRequest request3 = new SearchRequest(null, "1.0", ordering);
        SearchResponse response = getSearchService().execute(request3);

        assertEquals(2, response.getProducts().size());
        assertEquals("name1", response.getProducts().get(0).getName());
        assertEquals("description1", response.getProducts().get(0).getDescription());
        assertEquals("1.00", response.getProducts().get(0).getPrice().toPlainString());
        assertEquals("name2", response.getProducts().get(1).getName());
        assertEquals("description2", response.getProducts().get(1).getDescription());
        assertEquals("1.00", response.getProducts().get(1).getPrice().toPlainString());
    }

    @Test
    public void searchProductsByName_OrderingByPrice_Paging() {
        AddRequest request1 = new AddRequest("name", "description2", "2.0");
        getAddService().execute(request1);

        AddRequest request2 = new AddRequest("name", "description1", "1.0");
        getAddService().execute(request2);

        Ordering ordering = new Ordering("price", "ASCENDING");
        Logger.getAnonymousLogger("Is ok");
        Paging paging = new Paging(1, 1);
        SearchRequest request3 = new SearchRequest("name", null, ordering, paging);
        SearchResponse response = getSearchService().execute(request3);

        response.getProducts().stream().forEach(System.out::println);
        assertEquals(1, response.getProducts().size());
        assertEquals("name", response.getProducts().get(0).getName());
        assertEquals("description1", response.getProducts().get(0).getDescription());
        assertEquals("1.00", response.getProducts().get(0).getPrice().toPlainString());
    }

    private AddService getAddService() {
        return appContext.getBean(AddService.class);
    }

    private SearchService getSearchService() {
        return appContext.getBean(SearchService.class);
    }
}
