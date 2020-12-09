package internet_store.application.acceptancetests;

import internet_store.application.dependency_injection.ApplicationContext;
import internet_store.application.core.requests.AddProductRequest;
import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.DeleteByProductNameResponse;
import internet_store.application.core.services.AddProductService;
import internet_store.application.core.services.DeleteByProductNameService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DeleteByProductNameAcceptanceTest {

    /*private final ApplicationContext appContext = new ApplicationContext();

    @Test
    public void shouldDeleteProductCorrectly() {
        AddProductRequest request = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(request);
        DeleteByProductNameRequest requestToDelete = new DeleteByProductNameRequest("TV");
        DeleteByProductNameResponse response = getDeleteByProductNameService().execute(requestToDelete);
        assertTrue(response.isProductRemoved());
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldNotDeleteProductIfNameNotCorrect() {
        AddProductRequest request = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(request);
        DeleteByProductNameRequest requestToDelete = new DeleteByProductNameRequest("qwe");
        DeleteByProductNameResponse response = getDeleteByProductNameService().execute(requestToDelete);
        assertFalse(response.isProductRemoved());
    }

    @Test
    public void shouldNotDeleteProductIfNameNotEntered() {
        AddProductRequest request = new AddProductRequest(
                "TV", "SONY", new BigDecimal("1000"));
        getAddProductService().execute(request);
        DeleteByProductNameRequest requestToDelete = new DeleteByProductNameRequest("");
        DeleteByProductNameResponse response = getDeleteByProductNameService().execute(requestToDelete);
        assertFalse(response.isProductRemoved());
        assertTrue(response.hasErrors());
        assertEquals("Product name", response.getErrors().get(0).getField());
        assertEquals("must not be empty", response.getErrors().get(0).getMessage());
    }

        private AddProductService getAddProductService () {
            return appContext.getBean(AddProductService.class);
        }

        private DeleteByProductNameService getDeleteByProductNameService () {
            return appContext.getBean(DeleteByProductNameService.class);
        }*/

    }
