package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.AddNewProductCategoryRequest;
import estore.core.requests.AddNewProductRequest;
import estore.core.requests.GetAllProductsRequest;
import estore.core.responses.AddNewProductResponse;
import estore.core.responses.GetAllProductsResponse;
import estore.core.service.AddNewProductCategoryService;
import estore.core.service.AddNewProductService;
import estore.core.service.GetAllProductsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductsOnRequestTest {

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
    public void shouldAddValidProduct() {
        int dbInitialSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();

        AddNewProductCategoryRequest addNewProductCategoryRequest = new AddNewProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addNewProductCategoryRequest);

        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA", "1");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "1");
        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);

        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());

        assertEquals(response.getProducts().size(), dbInitialSize + 2);
        assertEquals(response.getProducts().get(dbInitialSize).getName(), "ProductA");
        assertEquals(response.getProducts().get(dbInitialSize + 1).getName(), "ProductB");

        response.getProducts().get(dbInitialSize).setPrice(1.25);
        assertEquals(response.getProducts().get(dbInitialSize).getPrice(), 1.25, 0.01);
    }

    @Test
    public void shouldFailAddingInvalidProduct() {
        int dbInitialSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();

        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("Product01", "Description ProductA", "Fruits");
        AddNewProductResponse addResponse = addNewProductService().execute(addProductRequest1);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must contain only english letters!");

        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("", "Description ProductB яя", "Fruits");
        addResponse = addNewProductService().execute(addProductRequest2);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must not be empty!");
        assertEquals(addResponse.getErrors().get(1).getMessage(), "Must contain only english letters and digits!");

        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);
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
