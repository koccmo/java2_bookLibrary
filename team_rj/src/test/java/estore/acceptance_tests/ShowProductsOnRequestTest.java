package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.AddNewProductRequest;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;
import estore.core.service.AddNewProductService;
import estore.core.service.ShowAllProductsService;
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
    }

    @Test
    public void shouldReturnCorrectProductList() {
        int dbInitialSize = getAllProductsService()
                .execute(new ShowAllProductsRequest())
                .getProducts()
                .size();
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        ShowAllProductsResponse response = getAllProductsService().execute(new ShowAllProductsRequest());

        assertEquals(response.getProducts().size(), dbInitialSize + 2);
    }

    private AddNewProductService addNewProductService() {
        return applicationContext.getBean(AddNewProductService.class);
    }

    private ShowAllProductsService getAllProductsService() {
        return applicationContext.getBean(ShowAllProductsService.class);
    }
}
