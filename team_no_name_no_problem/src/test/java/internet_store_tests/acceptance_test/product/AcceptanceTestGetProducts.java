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
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTestGetProducts {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }
    @Test
    public void test() {

        Product mobilePhone = new Product("Mobile phone", "Nokia", 45);
        Product tv = new Product("Tv", "Sony", 450);

        AddProductRequest addMobilePhoneRequest = new AddProductRequest(mobilePhone);
        AddProductRequest addTvRequest = new AddProductRequest(tv);

        addProductService().execute(addMobilePhoneRequest);
        addProductService().execute(addTvRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertEquals(getProductsResponse.getProducts().size(), 2);
    }

    private AddProductService addProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return appContext.getBean(GetAllProductsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner(){ return appContext.getBean(DatabaseCleaner.class);}
}
