package internet_store.acceptance_test;

import internet_store.ApplicationContext;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.FindProductByIdService;
import internet_store.core.services.product.GetAllProductsService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest2 {

    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void test(){
        Product product = new Product("Mobile phone", "Nokia", 50);
        product.setId(5L);
        FindByIdRequest findByIdRequest = new FindByIdRequest(5L);
        findProductByIdService().execute(findByIdRequest);
        findProductByIdService().execute(findByIdRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertTrue(getProductsResponse.getProducts().equals(product));
    }

    private FindProductByIdService findProductByIdService() {
        return applicationContext.getBean(FindProductByIdService.class);
    }

    private GetAllProductsService getAllProductsService(){
        return applicationContext.getBean(GetAllProductsService.class);
    }

}
