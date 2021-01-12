package lv.estore.app.core.acceptancetests;

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

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest2 {

    private ApplicationContext applicationContext;

    @Before
    public void setup(){
        applicationContext = new AnnotationConfigApplicationContext(EStoreConfiguration.class);
    }

    @Test
    public void searchProducts(){
        AddRequest addRequest1 = new AddRequest("product1", "description1", "1.1");
        getAddService().execute(addRequest1);

        AddRequest addRequest2 = new AddRequest("product2", "description2", "2.2");
        getAddService().execute(addRequest2);

        SearchRequest request3 = new SearchRequest("product1", null);
        SearchResponse response = getSearchService().execute(request3);

        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "product1");
        assertEquals(response.getProducts().get(0).getDescription(), "description1");
        assertEquals(response.getProducts().get(0).getPrice(), "1.1");
    }

    @Test
    public void searchProductsOrderingDescending() {
        AddRequest addRequest1 = new AddRequest("apple", "very deliciouse", "2.2");
        getAddService().execute(addRequest1);

        AddRequest addRequest2 = new AddRequest("apple", "very deliciouse", "1.1");
        getAddService().execute(addRequest2);

        AddRequest addRequest3 = new AddRequest("apple", "yumi yumi", "3.3");
        getAddService().execute(addRequest3);

        Ordering ordering = new Ordering("price", "DESCENDING");
        SearchRequest request4 = new SearchRequest("apple", null, ordering);
        SearchResponse response = getSearchService().execute(request4);

        assertEquals(response.getProducts().size(), 3);
        assertEquals(response.getProducts().get(0).getName(), "apple");
        assertEquals(response.getProducts().get(0).getPrice(), "3.3");
        assertEquals(response.getProducts().get(1).getName(), "apple");
        assertEquals(response.getProducts().get(1).getPrice(), "2.2");
        assertEquals(response.getProducts().get(2).getName(), "apple");
        assertEquals(response.getProducts().get(2).getPrice(), "1.1");
    }

    @Test
    public void searchProductsOrderingAscending(){
        AddRequest addRequest1 = new AddRequest("apple", "very deliciouse", "2.2");
        getAddService().execute(addRequest1);

        AddRequest addRequest2 = new AddRequest("apple", "very deliciouse", "1.1");
        getAddService().execute(addRequest2);

        AddRequest addRequest3 = new AddRequest("apple", "yumi yumi", "3.3");
        getAddService().execute(addRequest3);

        Ordering ordering = new Ordering("price", "ASCENDING");
        SearchRequest request4 = new SearchRequest("apple", null, ordering);
        SearchResponse response = getSearchService().execute(request4);

        assertEquals(response.getProducts().size(), 3);
        assertEquals(response.getProducts().get(0).getName(), "apple");
        assertEquals(response.getProducts().get(0).getPrice(), new BigDecimal("1.1"));
        assertEquals(response.getProducts().get(1).getName(), "apple");
        assertEquals(response.getProducts().get(1).getPrice(), new BigDecimal("2.2"));
        assertEquals(response.getProducts().get(2).getName(), "apple");
        assertEquals(response.getProducts().get(2).getPrice(), new BigDecimal("3.3"));
    }

    @Test
    public void searchProductsOrderingPaging() {
        AddRequest addRequest1 = new AddRequest("apple", "very deliciouse", "2.2");
        getAddService().execute(addRequest1);

        AddRequest addRequest2 = new AddRequest("apple", "very deliciouse", "1.1");
        getAddService().execute(addRequest2);

        AddRequest addRequest3 = new AddRequest("apple", "yumi yumi", "3.3");
        getAddService().execute(addRequest3);

        Ordering ordering = new Ordering("price", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchRequest request4 = new SearchRequest("apple", null, ordering, paging);
        SearchResponse response = getSearchService().execute(request4);

        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "apple");
        assertEquals(response.getProducts().get(0).getPrice(), new BigDecimal("1.1"));
    }

    private AddService getAddService(){
        return applicationContext.getBean(AddService.class);
    }

    private SearchService getSearchService(){
        return applicationContext.getBean(SearchService.class);
    }
}
