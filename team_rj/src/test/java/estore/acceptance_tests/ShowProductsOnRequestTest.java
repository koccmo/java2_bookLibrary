package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.AddNewProductCategoryRequest;
import estore.core.requests.AddNewProductRequest;
import estore.core.requests.GetAllProductsRequest;
import estore.core.responses.GetAllProductsResponse;
import estore.core.service.AddNewProductCategoryService;
import estore.core.service.AddNewProductService;
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
        AddNewProductCategoryRequest addNewProductCategoryRequest = new AddNewProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addNewProductCategoryRequest);

        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA", "1");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "1");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());

        assertEquals(response.getProducts().size(), 2);
    }

    private AddNewProductService addNewProductService() {
        return applicationContext.getBean(AddNewProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return applicationContext.getBean(GetAllProductsService.class);
    }

    private AddNewProductCategoryService AddNewProductCategoryService() {
        return applicationContext.getBean(AddNewProductCategoryService.class);
    }
}
