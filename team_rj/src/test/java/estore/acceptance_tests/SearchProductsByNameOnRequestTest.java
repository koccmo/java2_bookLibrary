package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.*;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.service.AddProductCategoryService;
import estore.core.service.AddProductService;
import estore.core.service.SearchProductByNameService;
import estore.core.service.UpdateProductByIdService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class SearchProductsByNameOnRequestTest {

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
    public void shouldSearchProductByName() {
        AddProductCategoryRequest addProductCategoryRequest = new AddProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addProductCategoryRequest);

        AddProductRequest addProductRequest1 = new AddProductRequest("ProductA", "Description ProductA1", "Category");
        AddProductRequest addProductRequest2 = new AddProductRequest("ProductB", "Description ProductB", "Category");
        AddProductRequest addProductRequest3 = new AddProductRequest("ProductA", "Description ProductA2", "Category");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA");
        SearchProductByNameResponse response = searchProductByNameService().execute(request);

        assertEquals(response.getProducts().size(), 2);
        assertEquals(response.getProducts().get(0).getName(), "ProductA");
        assertEquals(response.getProducts().get(0).getDescription(), "Description ProductA1");
        assertEquals(response.getProducts().get(1).getDescription(), "Description ProductA2");
    }

    @Test
    public void shouldSearchProductByNameDescending() {
        AddProductCategoryRequest addProductCategoryRequest = new AddProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addProductCategoryRequest);

        AddProductRequest addProductRequest1 = new AddProductRequest("ProductA", "Description ProductA1", "Category");
        AddProductRequest addProductRequest2 = new AddProductRequest("ProductB", "Description ProductB", "Category");
        AddProductRequest addProductRequest3 = new AddProductRequest("ProductA", "Description ProductA2", "Category");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        UpdateProductByIdRequest updateProductByIdRequest1 = new UpdateProductByIdRequest(
                1L, "ProductA", "Description ProductA1", "Category", "0", "1");
        UpdateProductByIdRequest updateProductByIdRequest2 = new UpdateProductByIdRequest(
                2L, "ProductB", "Description ProductB", "Category", "0", "2");
        UpdateProductByIdRequest updateProductByIdRequest3 = new UpdateProductByIdRequest(
                3L, "ProductA", "Description ProductA2", "Category", "0", "3");

        updateProductByIdService().execute(updateProductByIdRequest1);
        updateProductByIdService().execute(updateProductByIdRequest2);
        updateProductByIdService().execute(updateProductByIdRequest3);

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
        AddProductCategoryRequest addProductCategoryRequest = new AddProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addProductCategoryRequest);

        AddProductRequest addProductRequest1 = new AddProductRequest("ProductA", "Description ProductA1", "Category");
        AddProductRequest addProductRequest2 = new AddProductRequest("ProductB", "Description ProductB", "Category");
        AddProductRequest addProductRequest3 = new AddProductRequest("ProductA", "Description ProductA2", "Category");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        UpdateProductByIdRequest updateProductByIdRequest1 = new UpdateProductByIdRequest(
                1L, "ProductA", "Description ProductA1", "Category", "0", "3");
        UpdateProductByIdRequest updateProductByIdRequest2 = new UpdateProductByIdRequest(
                2L, "ProductB", "Description ProductB", "Category", "0", "2");
        UpdateProductByIdRequest updateProductByIdRequest3 = new UpdateProductByIdRequest(
                3L, "ProductA", "Description ProductA2", "Category", "0", "1");

        updateProductByIdService().execute(updateProductByIdRequest1);
        updateProductByIdService().execute(updateProductByIdRequest2);
        updateProductByIdService().execute(updateProductByIdRequest3);

        Ordering ordering = new Ordering("price", "asc");
        Paging paging = new Paging("1", "1");
        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA", ordering, paging);
        SearchProductByNameResponse response = searchProductByNameService().execute(request);

        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "ProductA");
        assertEquals(response.getProducts().get(0).getPrice(), 1, 0.01);
    }

    private AddProductService addNewProductService() {
        return applicationContext.getBean(AddProductService.class);
    }

    private SearchProductByNameService searchProductByNameService() {
        return applicationContext.getBean(SearchProductByNameService.class);
    }

    private AddProductCategoryService AddNewProductCategoryService() {
        return applicationContext.getBean(AddProductCategoryService.class);
    }

    private UpdateProductByIdService updateProductByIdService() {
        return applicationContext.getBean(UpdateProductByIdService.class);
    }
}
