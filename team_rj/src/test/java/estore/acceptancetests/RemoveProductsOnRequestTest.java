package estore.acceptancetests;

import estore.ApplicationContext;
import estore.core.requests.AddNewProductRequest;
import estore.core.requests.RemoveProductByIdRequest;
import estore.core.requests.RemoveProductByNameRequest;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.RemoveProductByIdResponse;
import estore.core.responses.RemoveProductByNameResponse;
import estore.core.responses.ShowAllProductsResponse;
import estore.core.service.AddNewProductService;
import estore.core.service.RemoveProductByIdService;
import estore.core.service.RemoveProductByNameService;
import estore.core.service.ShowAllProductsService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemoveProductsOnRequestTest {
    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldRemoveProductByNameAndId() {
        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA", "Fruits");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductC", "Description ProductC", "Fruits");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        int dbInitialSize = getAllProductsService()
                .execute(new ShowAllProductsRequest())
                .getProducts()
                .size();

        RemoveProductByNameRequest removeByNameRequest = new RemoveProductByNameRequest("ProductA");
        RemoveProductByNameResponse removeByNameResponse = removeProductByNameService().execute(removeByNameRequest);
        assertEquals(removeByNameResponse.getProductsRemoved(), 1);

        ShowAllProductsResponse response = getAllProductsService().execute(new ShowAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize - 1);

        RemoveProductByIdRequest removeByIdRequest = new RemoveProductByIdRequest(response.getProducts().size() + "");
        RemoveProductByIdResponse removeByIdResponse = removeProductByIdService().execute(removeByIdRequest);
        assertEquals(removeByIdResponse.getProductsRemoved(), 1);
    }

    @Test
    public void shouldFailToRemoveProductByNameAndIdIfDoesNotExist() {
        int dbInitialSize = getAllProductsService()
                .execute(new ShowAllProductsRequest())
                .getProducts()
                .size();

        RemoveProductByNameRequest removeByNameRequest = new RemoveProductByNameRequest("NoSuchProduct");
        RemoveProductByNameResponse removeByNameResponse = removeProductByNameService().execute(removeByNameRequest);
        assertEquals(removeByNameResponse.getProductsRemoved(), 0);

        ShowAllProductsResponse response = getAllProductsService().execute(new ShowAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);

        RemoveProductByIdRequest removeByIdRequest = new RemoveProductByIdRequest(response.getProducts().size() + 1 + "");
        RemoveProductByIdResponse removeByIdResponse = removeProductByIdService().execute(removeByIdRequest);
        assertEquals(removeByIdResponse.getProductsRemoved(), 0);

        response = getAllProductsService().execute(new ShowAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);
    }

    @Test
    public void shouldFailToRemoveProductByNameAndIdIfInvalid() {
        int dbInitialSize = getAllProductsService()
                .execute(new ShowAllProductsRequest())
                .getProducts()
                .size();

        RemoveProductByNameRequest removeByNameRequest1 = new RemoveProductByNameRequest("");
        RemoveProductByNameResponse removeByNameResponse = removeProductByNameService().execute(removeByNameRequest1);

        ShowAllProductsResponse response = getAllProductsService().execute(new ShowAllProductsRequest());
        assertEquals(response.getProducts().size(), dbInitialSize);
        assertTrue(removeByNameResponse.hasErrors());
        assertEquals(removeByNameResponse.getErrors().get(0).getMessage(), "Must not be empty!");

        RemoveProductByNameRequest removeByNameRequest2 = new RemoveProductByNameRequest("Invalid 01");
        removeByNameResponse = removeProductByNameService().execute(removeByNameRequest2);
        assertEquals(response.getProducts().size(), dbInitialSize);
        assertTrue(removeByNameResponse.hasErrors());
        assertEquals(removeByNameResponse.getErrors().get(0).getMessage(), "Must contain only english letters!");

        RemoveProductByIdRequest removeByIdRequest = new RemoveProductByIdRequest("Invalid id");
        RemoveProductByIdResponse removeByIdResponse = removeProductByIdService().execute(removeByIdRequest);
        assertEquals(response.getProducts().size(), dbInitialSize);
        assertTrue(removeByIdResponse.hasErrors());
        assertEquals(removeByIdResponse.getErrors().get(0).getMessage(), "Must contain only digits");
    }

    private AddNewProductService addNewProductService() {
        return applicationContext.getBean(AddNewProductService.class);
    }

    private RemoveProductByNameService removeProductByNameService() {
        return applicationContext.getBean(RemoveProductByNameService.class);
    }

    private RemoveProductByIdService removeProductByIdService() {
        return applicationContext.getBean(RemoveProductByIdService.class);
    }

    private ShowAllProductsService getAllProductsService() {
        return applicationContext.getBean(ShowAllProductsService.class);
    }
}
