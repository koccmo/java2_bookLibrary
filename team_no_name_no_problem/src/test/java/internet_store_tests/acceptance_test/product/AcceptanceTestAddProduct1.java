package internet_store_tests.acceptance_test.product;

import internet_store.core.DatabaseCleaner;
import internet_store.config.SpringCoreConfiguration;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.GetAllProductsService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestAddProduct1 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }
    @Test
    public void test(){
        Product product = new Product("Milk", "10%", 12);
        AddProductRequest addProductRequest = new AddProductRequest(product);
        addProductService().execute(addProductRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertTrue(getProductsResponse.getProducts().size() == 1);

    }

    private AddProductService addProductService(){
        return appContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService(){
        return appContext.getBean(GetAllProductsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner(){ return appContext.getBean(DatabaseCleaner.class);}
}
