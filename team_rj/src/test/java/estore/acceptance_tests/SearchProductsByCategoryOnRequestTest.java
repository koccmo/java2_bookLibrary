package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.*;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.service.AddProductCategoryService;
import estore.core.service.AddProductService;
import estore.core.service.SearchProductByCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchProductsByCategoryOnRequestTest {

    private static ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(ProductConfiguration.class);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    @Test
    public void shouldSearchProductByCategoryDescendingAndPaging() {
        AddProductCategoryRequest addProductCategoryRequest = new AddProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addProductCategoryRequest);

        AddProductRequest addProductRequest1 = new AddProductRequest("ZzProductA", "Description ProductA", "Category");
        AddProductRequest addProductRequest2 = new AddProductRequest("ZzProductB", "Description ProductB", "Category");
        AddProductRequest addProductRequest3 = new AddProductRequest("ZzProductC", "Description ProductC", "Category");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        Ordering ordering = new Ordering("name", "desc");
        Paging paging = new Paging("2", "1");
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category", ordering, paging);
        SearchProductByCategoryResponse response = searchProductByCategoryService().execute(request);

        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "ZzProductB");
    }

    @Test
    public void shouldReturnErrorsOnSearchProductByCategoryIfInvalidData() {
        AddProductCategoryRequest addProductCategoryRequest = new AddProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addProductCategoryRequest);

        AddProductRequest addProductRequest1 = new AddProductRequest("ProductA", "Description ProductA1", "Category");
        AddProductRequest addProductRequest2 = new AddProductRequest("ProductB", "Description ProductB", "Category");
        AddProductRequest addProductRequest3 = new AddProductRequest("ProductA", "Description ProductA2", "Category");

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

    private AddProductService addNewProductService() {
        return applicationContext.getBean(AddProductService.class);
    }

    private SearchProductByCategoryService searchProductByCategoryService() {
        return applicationContext.getBean(SearchProductByCategoryService.class);
    }

    private AddProductCategoryService AddNewProductCategoryService() {
        return applicationContext.getBean(AddProductCategoryService.class);
    }
}
