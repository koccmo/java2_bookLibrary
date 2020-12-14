package internet_store_tests.acceptance_test.product;

import internet_store.config.MainMenuConfiguration;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.DeleteByIdService;
import internet_store.core.services.product.GetAllProductsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTestDeleteProduct2 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
    @Test
    public void test() {

        Product mobilePhone = new Product("Mobile phone", "Nokia", 45);
        Product tv = new Product("Tv", "Sony", 450);

        AddProductRequest requestForPhone = new AddProductRequest(mobilePhone);
        AddProductRequest requestForTv = new AddProductRequest(tv);

        addProductService().execute(requestForPhone);
        addProductService().execute(requestForTv);

        DeleteProductRequest deletePhoneRequest = new DeleteProductRequest(1L);
        deleteProductByIdService().execute(deletePhoneRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertEquals(getProductsResponse.getProducts().size(),1);
    }

    private AddProductService addProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return appContext.getBean(GetAllProductsService.class);
    }

    private DeleteByIdService deleteProductByIdService(){
        return appContext.getBean(DeleteByIdService.class);
    }
}