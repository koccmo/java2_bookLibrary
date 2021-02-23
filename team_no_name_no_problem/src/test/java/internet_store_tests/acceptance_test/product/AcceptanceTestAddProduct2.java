package internet_store_tests.acceptance_test.product;

import internet_store.core.DatabaseCleaner;
import internet_store.config.SpringCoreConfiguration;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.SearchProductByIdRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.SearchProductByIdResponse;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.SearchProductByIdService;
import internet_store.core.services.product.GetAllProductsService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AcceptanceTestAddProduct2 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }
    @Test
    public void test(){
        Product product = new Product("Mobile phone", "Nokia", 50);

        AddProductRequest addProductRequest = new AddProductRequest(product);
        addProductService().execute(addProductRequest);

        SearchProductByIdRequest findByIdRequest = new SearchProductByIdRequest(5L);
        SearchProductByIdResponse findByIdResponse = findProductByIdService().execute(findByIdRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertFalse(getProductsResponse.hasErrors());
        assertTrue(getProductsResponse.getProducts().size() == 1);
        assertTrue(findByIdResponse.hasErrors());
    }

    private AddProductService addProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private SearchProductByIdService findProductByIdService() {
        return appContext.getBean(SearchProductByIdService.class);
    }

    private GetAllProductsService getAllProductsService(){
        return appContext.getBean(GetAllProductsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner(){ return appContext.getBean(DatabaseCleaner.class);}

}
