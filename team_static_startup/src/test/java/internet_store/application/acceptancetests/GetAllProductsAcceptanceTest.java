package internet_store.application.acceptancetests;

import internet_store.application.core.requests.AddProductRequest;
import internet_store.application.core.requests.GetAllProductsRequest;
import internet_store.application.core.responses.GetAllProductsResponse;
import internet_store.application.core.services.AddProductService;
import internet_store.application.core.services.GetAllProductsService;
import internet_store.application.dependency_injection.ApplicationContext;
import internet_store.application.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GetAllProductsAcceptanceTest {

    private final ApplicationContext appContext =
            new DIApplicationContextBuilder().build("internet_store.application");

    @Test
    public void shouldReturnCorrectProductList() {
        AddProductRequest addProductRequest1 = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(addProductRequest1);

        AddProductRequest addBookRequest2 = new AddProductRequest(
                "Receiver", "Denon", new BigDecimal("1500"));
        getAddProductService().execute(addBookRequest2);

        GetAllProductsResponse response = getAllProductsService().execute(new GetAllProductsRequest());
        assertEquals(response.getProductList().size(), 2);
    }

    private AddProductService getAddProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return appContext.getBean(GetAllProductsService.class);
    }

}