package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.domain.Product;
import estore.core.requests.*;
import estore.core.responses.GetAllProductsResponse;
import estore.core.responses.RemoveProductByIdResponse;
import estore.core.responses.RemoveProductByNameResponse;
import estore.core.service.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemoveProductsOnRequestTest {

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
    public void shouldRemoveProductByNameAndId() {
        AddProductCategoryRequest addProductCategoryRequest = new AddProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addProductCategoryRequest);

        AddProductRequest addProductRequest1 = new AddProductRequest("ProductA", "Description ProductA", "Category");
        AddProductRequest addProductRequest2 = new AddProductRequest("ProductB", "Description ProductB", "Category");
        AddProductRequest addProductRequest3 = new AddProductRequest("ProductC", "Description ProductC", "Category");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        int dbInitialSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();

        RemoveProductByNameRequest removeByNameRequest = new RemoveProductByNameRequest("ProductA");
        RemoveProductByNameResponse removeByNameResponse = removeProductByNameService().execute(removeByNameRequest);

        int currentSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();
        assertEquals(3, dbInitialSize);
        assertEquals(2, currentSize);

        RemoveProductByIdRequest removeByIdRequest = new RemoveProductByIdRequest("3");
        RemoveProductByIdResponse removeByIdResponse = removeProductByIdService().execute(removeByIdRequest);

        List<Product> productList = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts();
        assertEquals(1, productList.size());
        assertEquals("ProductB", productList.get(0).getName());
    }

    @Test
    public void shouldFailToRemoveProductByNameAndIdIfDoesNotExist() {
        int dbInitialSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();

        RemoveProductByNameRequest removeByNameRequest = new RemoveProductByNameRequest("NoSuchProduct");
        RemoveProductByNameResponse removeByNameResponse = removeProductByNameService().execute(removeByNameRequest);
        int currentSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();
        assertEquals(dbInitialSize, currentSize);

        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);

        RemoveProductByIdRequest removeByIdRequest = new RemoveProductByIdRequest(response.getProducts().size() + 1 + "");
        RemoveProductByIdResponse removeByIdResponse = removeProductByIdService().execute(removeByIdRequest);
        currentSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();
        assertEquals(dbInitialSize, currentSize);

        response = getAllProductsService().execute(new GetAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);
    }

    @Test
    public void shouldFailToRemoveProductByNameAndIdIfInvalid() {
        int dbInitialSize = getAllProductsService()
                .execute(new GetAllProductsRequest())
                .getProducts()
                .size();

        RemoveProductByNameRequest removeByNameRequest1 = new RemoveProductByNameRequest("");
        RemoveProductByNameResponse removeByNameResponse = removeProductByNameService().execute(removeByNameRequest1);

        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);
        assertTrue(removeByNameResponse.hasErrors());
        assertEquals(removeByNameResponse.getErrors().get(0).getMessage(), "Must not be empty!");

        RemoveProductByNameRequest removeByNameRequest2 = new RemoveProductByNameRequest("Invalid 01");
        removeByNameResponse = removeProductByNameService().execute(removeByNameRequest2);
        assertEquals(response.getProducts().size(), dbInitialSize);
        assertTrue(removeByNameResponse.hasErrors());
        assertEquals(removeByNameResponse.getErrors().get(0).getMessage(), "Must contain only english letters!");

        RemoveProductByIdRequest removeByIdRequest = new RemoveProductByIdRequest("Invalid id");
        RemoveProductByIdResponse removeByIdResponse = removeProductByIdService().execute(removeByIdRequest);
        assertEquals(response.getProducts().size(), dbInitialSize);
        assertTrue(removeByIdResponse.hasErrors());
        assertEquals(removeByIdResponse.getErrors().get(0).getMessage(), "Must contain only digits");
    }

    private AddProductService addNewProductService() {
        return applicationContext.getBean(AddProductService.class);
    }

    private RemoveProductByNameService removeProductByNameService() {
        return applicationContext.getBean(RemoveProductByNameService.class);
    }

    private RemoveProductByIdService removeProductByIdService() {
        return applicationContext.getBean(RemoveProductByIdService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return applicationContext.getBean(GetAllProductsService.class);
    }

    private AddProductCategoryService AddNewProductCategoryService() {
        return applicationContext.getBean(AddProductCategoryService.class);
    }
}
