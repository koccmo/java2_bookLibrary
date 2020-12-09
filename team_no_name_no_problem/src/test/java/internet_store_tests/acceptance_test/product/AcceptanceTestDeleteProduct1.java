package internet_store_tests.acceptance_test.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.DeleteByIdService;
import internet_store.core.services.product.GetAllProductsService;
import internet_store.dependency_injection.ApplicationContext;
import internet_store.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceTestDeleteProduct1 {
/*
    private static ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("internet_store");
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
        return applicationContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return applicationContext.getBean(GetAllProductsService.class);
    }

    private DeleteByIdService deleteProductByIdService(){
        return applicationContext.getBean(DeleteByIdService.class);
    }*/
}


