package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.request.products.UpdateProductByIdRequest;
import lv.estore.app.core.responses.products.UpdateProductResponse;
import lv.estore.app.core.services.products.UpdateProductByIdService;
import lv.estore.app.core.validators.products.ProductUpdateValidator;
import lv.estore.app.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UpdateProductByIdServiceTest {

    @Mock
    ProductUpdateValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;
    @Mock
    CommonUtils utils;

    @InjectMocks
    UpdateProductByIdService service;

    @Test
    public void testUpdateProductSuccess(){
        BigDecimal price = new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.updateProductById(any(), any(), any(), any())).thenReturn(true);
        UpdateProductByIdRequest request = new UpdateProductByIdRequest("1", "name", "description", "1.0");
        UpdateProductResponse response = service.execute(request);

        assertTrue(response.isProductUpdated());
        assertFalse(response.hasErrors());

        Mockito.verify(database, Mockito.atLeastOnce())
                .updateProductById(1L, "name", "description", price);
    }

    @Test
    public void testUpdateProductError(){
        UpdateProductByIdRequest request = new UpdateProductByIdRequest(null, "description", "1.0", "1.0");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        UpdateProductResponse response = service.execute(request);

        assertFalse(response.isProductUpdated());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }
}