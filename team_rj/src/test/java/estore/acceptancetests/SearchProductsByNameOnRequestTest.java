package estore.acceptancetests;

import estore.ApplicationContext;
import estore.core.requests.*;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.service.*;
import estore.database.ProductDB;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchProductsByNameOnRequestTest {
    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldSearchProductByName() {
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA1", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductA", "Description ProductA2", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA");
        SearchProductByNameResponse response = searchProductByNameService().execute(request);

        assertEquals(response.getProductsFound(), 2);
        assertEquals(response.getProducts().size(), 2);
        assertEquals(response.getProducts().get(0).getName(), "ProductA");
        assertEquals(response.getProducts().get(0).getDescription(), "Description ProductA1");
        assertEquals(response.getProducts().get(1).getDescription(), "Description ProductA2");
    }

    @Test
    public void shouldSearchProductByNameDescending() {
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA1", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductA", "Description ProductA2", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        int dbSize = getProductDb().getDatabaseSize();
        getProductDb().getDatabase().get(dbSize-1).setPrice(3);
        getProductDb().getDatabase().get(dbSize-2).setPrice(2);
        getProductDb().getDatabase().get(dbSize-3).setPrice(1);

        Ordering ordering = new Ordering("price", "desc");
        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA", ordering);
        SearchProductByNameResponse response = searchProductByNameService().execute(request);

        assertEquals(response.getProducts().size(), 2);
        assertEquals(response.getProducts().get(0).getName(), "ProductA");
        assertEquals(response.getProducts().get(0).getPrice(), 3, 0.01);
        assertEquals(response.getProducts().get(1).getPrice(), 1, 0.01);
    }

    @Test
    public void shouldSearchProductByNameAscendingAndPaging() {
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA1", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductA", "Description ProductA2", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        int dbSize = getProductDb().getDatabaseSize();
        getProductDb().getDatabase().get(dbSize-1).setPrice(1);
        getProductDb().getDatabase().get(dbSize-2).setPrice(2);
        getProductDb().getDatabase().get(dbSize-3).setPrice(3);

        Ordering ordering = new Ordering("price", "asc");
        Paging paging = new Paging("1", "1");
        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA", ordering, paging);
        SearchProductByNameResponse response = searchProductByNameService().execute(request);

        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "ProductA");
        assertEquals(response.getProducts().get(0).getPrice(), 1, 0.01);
    }

    private AddNewProductService addNewProductService() {
        return applicationContext.getBean(AddNewProductService.class);
    }

    private SearchProductByNameService searchProductByNameService() {
        return applicationContext.getBean(SearchProductByNameService.class);
    }

    private ProductDB getProductDb() {
        return applicationContext.getBean(ProductDB.class);
    }
}
