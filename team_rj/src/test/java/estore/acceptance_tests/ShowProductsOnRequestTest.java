package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.AddProductCategoryRequest;
import estore.core.requests.AddProductRequest;
import estore.core.requests.GetAllProductsRequest;
import estore.core.responses.GetAllProductsResponse;
import estore.core.service.AddProductCategoryService;
import estore.core.service.AddProductService;
import estore.core.service.GetAllProductsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class ShowProductsOnRequestTest {

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
    public void shouldReturnCorrectProductList() {
        AddProductCategoryRequest addProductCategoryRequest = new AddProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addProductCategoryRequest);

        AddProductRequest addProductRequest1 = new AddProductRequest("ProductA", "Description ProductA", "Category");
        AddProductRequest addProductRequest2 = new AddProductRequest("ProductB", "Description ProductB", "Category");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());

        assertEquals(response.getProducts().size(), 2);
    }

    private AddProductService addNewProductService() {
        return applicationContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return applicationContext.getBean(GetAllProductsService.class);
    }

    private AddProductCategoryService AddNewProductCategoryService() {
        return applicationContext.getBean(AddProductCategoryService.class);
    }
}
