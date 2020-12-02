package internet_store.application.acceptancetests;

import internet_store.application.ApplicationContext;
import internet_store.application.core.requests.AddProductRequest;
import internet_store.application.core.responses.PrintProductsToConsoleResponse;
import internet_store.application.core.services.AddProductService;
import internet_store.application.core.services.GetProductListService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AddProductAcceptanceTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp(){
        applicationContext = new ApplicationContext();
    }

    @Test
    public void shouldReturnNoErrorsWhenAddingBooks() {
        AddProductRequest addProductRequest1 = new AddProductRequest("tv1",
                "good tv1", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest1);

        AddProductRequest addProductRequest2 = new AddProductRequest("tv2",
                "good tv2", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest2);

        PrintProductsToConsoleResponse response = getProductListService().execute();
        assertEquals(2, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterName() {
        AddProductRequest addProductRequest = new AddProductRequest(" ",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest);

        PrintProductsToConsoleResponse response = getProductListService().execute();
        assertEquals(0, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterDescription() {
        AddProductRequest addProductRequest = new AddProductRequest("tv",
                null, new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest);

        PrintProductsToConsoleResponse response = getProductListService().execute();
        assertEquals(0, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterPrice() {
        AddProductRequest addProductRequest = new AddProductRequest("tv",
                "good tv", null);
        getAddProductService().execute(addProductRequest);

        PrintProductsToConsoleResponse response = getProductListService().execute();
        assertEquals(0, response.getProductList().size());
    }

    private AddProductService getAddProductService() {
        return applicationContext.getBean(AddProductService.class);
    }

    private GetProductListService getProductListService() {
        return applicationContext.getBean(GetProductListService.class);
    }

}
