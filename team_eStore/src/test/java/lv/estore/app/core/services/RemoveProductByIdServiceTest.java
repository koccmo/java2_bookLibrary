package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.RemoveProductResponse;
import lv.estore.app.core.services.products.RemoveProductByIdService;
import lv.estore.app.core.validators.products.ProductIdValidator;
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
public class RemoveProductByIdServiceTest {

    @Mock
    ProductIdValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;

    @InjectMocks
    RemoveProductByIdService service;

    @Test
    public void testRemoveById_Success() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.removeProductById(1L)).thenReturn(true);
        ProductIdRequest request = new ProductIdRequest("1");
        RemoveProductResponse response = service.execute(request);

        assertTrue(response.isProductRemoved());
        assertFalse(response.hasErrors());
    }

    @Test
    public void testRemoveById_Error() {
        ProductIdRequest request = new ProductIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Id", "Field should not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveProductResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().size() != 0);
        assertTrue(response.getErrors()
                .stream().anyMatch(s -> s.getField().equals("Id")
                                        && s.getMessage().equals("Field should not be empty")));
    }
}