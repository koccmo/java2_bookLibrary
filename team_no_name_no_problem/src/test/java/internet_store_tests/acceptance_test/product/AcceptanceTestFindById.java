package internet_store_tests.acceptance_test.product;
/*
import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.FindByIdResponse;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.FindProductByIdService;
import internet_store.core.services.product.GetAllProductsService;
import internet_store.dependency_injection.ApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcceptanceTestFindById {

    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void test() {

        Product mobilePhone = new Product("Mobile phone", "Nokia", 45);
        Product tv = new Product("Tv", "Sony", 450);

        AddProductRequest addMobilePhoneRequest = new AddProductRequest(mobilePhone);
        AddProductRequest addTvRequest = new AddProductRequest(tv);

        addProductService().execute(addMobilePhoneRequest);
        addProductService().execute(addTvRequest);

        FindByIdRequest findByIdRequest = new FindByIdRequest(1L);
        FindByIdResponse findByIdResponse = findProductByIdService().execute(findByIdRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertEquals(getProductsResponse.getProducts().size(), 1);
        assertTrue(findByIdResponse.getProduct().equals(mobilePhone));
    }

    private AddProductService addProductService() {
        return applicationContext.getBean(AddProductService.class);
    }

    private FindProductByIdService findProductByIdService() {
        return applicationContext.getBean(FindProductByIdService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return applicationContext.getBean(GetAllProductsService.class);
    }
}

 */