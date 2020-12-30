package estore.acceptancetests;

import estore.ApplicationContext;
import estore.core.requests.AddNewProductRequest;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.AddNewProductResponse;
import estore.core.responses.ShowAllProductsResponse;
import estore.core.service.AddNewProductService;
import estore.core.service.ShowAllProductsService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductsOnRequestTest {
    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldAddValidProduct() {
        int dbInitialSize = getAllProductsService()
                .execute(new ShowAllProductsRequest())
                .getProducts()
                .size();
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        ShowAllProductsResponse response = getAllProductsService().execute(new ShowAllProductsRequest());

        assertEquals(response.getProducts().size(), dbInitialSize + 2);
        assertEquals(response.getProducts().get(dbInitialSize).getName(), "ProductA");
        assertEquals(response.getProducts().get(dbInitialSize + 1).getName(), "ProductB");

        response.getProducts().get(dbInitialSize).setPrice(1.25);
        assertEquals(response.getProducts().get(dbInitialSize).getPrice(), 1.25, 0.01);
    }

    @Test
    public void shouldFailAddingInvalidProduct() {
        int dbInitialSize = getAllProductsService()
                .execute(new ShowAllProductsRequest())
                .getProducts()
                .size();

        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("Product01", "Description ProductA", "Fruits");
        AddNewProductResponse addResponse = addNewProductService().execute(addProductRequest1);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must contain only english letters!");

        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("", "Description ProductB яя", "Fruits");
        addResponse = addNewProductService().execute(addProductRequest2);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must not be empty!");
        assertEquals(addResponse.getErrors().get(1).getMessage(), "Must contain only english letters and digits!");

        ShowAllProductsResponse response = getAllProductsService().execute(new ShowAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);
    }

    private AddNewProductService addNewProductService() {
        return applicationContext.getBean(AddNewProductService.class);
    }

    private ShowAllProductsService getAllProductsService() {
        return applicationContext.getBean(ShowAllProductsService.class);
    }
}
