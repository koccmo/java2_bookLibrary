package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.responses.products.RemoveProductResponse;
import lv.estore.app.core.services.products.RemoveProductByNameService;
import lv.estore.app.core.validators.products.ProductNameValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductByNameServiceTest {

    @Mock
    ProductNameValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;

    @InjectMocks
    RemoveProductByNameService service;

    @Test
    public void testRemoveByName_Success() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.removeProductByName("name")).thenReturn(true);
        ProductNameRequest request = new ProductNameRequest("name");
        RemoveProductResponse response = service.execute(request);

        assertTrue(response.isProductRemoved());
        assertFalse(response.hasErrors());
    }

    @Test
    public void testRemoveById_Error() {
        ProductNameRequest request = new ProductNameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveProductResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().size() != 0);
        assertTrue(response.getErrors()
                .stream().anyMatch(s -> s.getField().equals("Name")
                        && s.getMessage().equals("Field should not be empty")));
    }
}