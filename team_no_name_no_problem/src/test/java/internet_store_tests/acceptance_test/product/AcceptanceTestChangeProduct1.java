package internet_store_tests.acceptance_test.product;

import internet_store.WebInternetStoreApplication;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.ChangeProductService;
import internet_store.core.services.product.GetAllProductsService;
import internet_store.dependency_injection.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcceptanceTestChangeProduct1 {
/*
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(WebInternetStoreApplication.class);
    }
    @Test
    public void test() {

        Product mobilePhone = new Product("Mobile phone", "Nokia", 45);

        AddProductRequest addMobilePhoneRequest = new AddProductRequest(mobilePhone);
        addProductService().execute(addMobilePhoneRequest);

        ChangeProductRequest changeProductRequest = new ChangeProductRequest(1L,"Cell phone",
                                                                    "",null);
        ChangeProductResponse changeProductResponse = changeProductService().execute(changeProductRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertEquals(getProductsResponse.getProducts().size(), 1);
        assertTrue(!changeProductResponse.hasErrors());
        assertTrue(getProductsResponse.getProducts().get(0).getTitle().equals("Cell phone"));
    }

    private AddProductService addProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private ChangeProductService changeProductService() {
        return appContext.getBean(ChangeProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return appContext.getBean(GetAllProductsService.class);
    }
    
*/
}