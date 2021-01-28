package internet_store.application.inmemory_acceptancetests;

import internet_store.lesson_6.config.ProductListConfiguration;
import internet_store.lesson_6.core.requests.AddProductRequest;
import internet_store.lesson_6.core.requests.DeleteByProductNameRequest;
import internet_store.lesson_6.core.responses.DeleteByProductNameResponse;
import internet_store.lesson_6.core.services.AddProductService;
import internet_store.lesson_6.core.services.DeleteByProductNameService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.junit.Assert.*;

// @Profile("inmemory")
public class DeleteByProductNameAcceptanceTest {

    private final ApplicationContext appContext =
            new AnnotationConfigApplicationContext(ProductListConfiguration.class);

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
        }
    }
