package estore.acceptancetests;

import estore.dependency_injection.ApplicationContext;
import estore.core.requests.*;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.service.AddNewProductService;
import estore.core.service.SearchProductByCategoryService;
import estore.database.ProductDB;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchProductsByCategoryOnRequestTest {
    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldSearchProductByCategoryDescendingAndPaging() {
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ZzProductA", "Description ProductA", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ZzProductB", "Description ProductB", "Fruits");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ZzProductC", "Description ProductC", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        Ordering ordering = new Ordering("name", "desc");
        Paging paging = new Paging("2", "1");
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Fruits", ordering, paging);
        SearchProductByCategoryResponse response = searchProductByCategoryService().execute(request);

        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "ZzProductB");
    }

    @Test
    public void shouldReturnErrorsOnSearchProductByCategoryIfInvalidData() {
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA1", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductA", "Description ProductA2", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        SearchProductByCategoryRequest request1 = new SearchProductByCategoryRequest("");
        SearchProductByCategoryResponse response = searchProductByCategoryService().execute(request1);

        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");

        Ordering ordering = new Ordering("price", "Invalid");
        Paging paging = new Paging("-1", "1");
        SearchProductByCategoryRequest request2 = new SearchProductByCategoryRequest("Fruits01", ordering, paging);
        response = searchProductByCategoryService().execute(request2);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 3);
        assertEquals(response.getErrors().get(0).getMessage(), "Must contain only english letters!");
        assertEquals(response.getErrors().get(1).getField(), "orderDirection");
        assertEquals(response.getErrors().get(1).getMessage(), "Must contain 'ASCENDING/asc' or 'DESCENDING/desc' only!");
        assertEquals(response.getErrors().get(2).getField(), "Page Number");
        assertEquals(response.getErrors().get(2).getMessage(), "Must be positive integer!");
    }

    private AddNewProductService addNewProductService() {
        return applicationContext.getBean(AddNewProductService.class);
    }

    private SearchProductByCategoryService searchProductByCategoryService() {
        return applicationContext.getBean(SearchProductByCategoryService.class);
    }

    private ProductDB getProductDb() {
        return applicationContext.getBean(ProductDB.class);
    }
}
