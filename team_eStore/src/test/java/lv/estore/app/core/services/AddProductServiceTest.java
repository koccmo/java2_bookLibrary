package lv.estore.app.core.services;

import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.AddProductRequest;
import lv.estore.app.core.responses.products.AddProductResponse;
import lv.estore.app.core.services.products.AddProductService;
import lv.estore.app.core.validators.products.AddProductValidator;
import lv.estore.app.matchers.ProductMatcher;
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
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {

    @Mock
    AddProductValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;
    @Mock CommonUtils utils;

    @InjectMocks
    AddProductService service;

    @Test
    public void testAddProductSuccess(){
        BigDecimal price = new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.addProduct(any())).thenReturn(1l);
        AddProductRequest request = new AddProductRequest("name", "description", "1.0");
        AddProductResponse response = service.execute(request);

        assertTrue(response.isProductAdded());
        assertFalse(response.hasErrors());

        Mockito.verify(database).addProduct(
                argThat(new ProductMatcher("name", price)));
    }

    @Test
    public void testAddProductError(){
        AddProductRequest request = new AddProductRequest(null, "description", "1.0");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        AddProductResponse response = service.execute(request);

        assertFalse(response.isProductAdded());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }
}