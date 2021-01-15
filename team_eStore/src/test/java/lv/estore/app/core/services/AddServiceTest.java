package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.repository.ProductDatabase;
import lv.estore.app.core.request.AddRequest;
import lv.estore.app.core.responses.AddResponse;
import lv.estore.app.core.validators.AddValidator;
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
public class AddServiceTest {

    @Mock AddValidator validator;
    @Mock ProductDatabase database;
    @Mock CommonUtils utils;

    @InjectMocks AddService service;

    @Test
    public void testAddProductSuccess(){
        BigDecimal price = new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(utils.createBigDecimal(any())).thenReturn(price);
        Mockito.when(database.addProduct(any())).thenReturn(true);
        AddRequest request = new AddRequest("name", "description", "1.0");
        AddResponse response = service.execute(request);

        assertTrue(response.isProductAdded());
        assertFalse(response.hasErrors());

        Mockito.verify(database).addProduct(
                argThat(new ProductMatcher("name", price)));
    }

    @Test
    public void testAddProductError(){
        AddRequest request = new AddRequest(null, "description", "1.0");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        AddResponse response = service.execute(request);

        assertFalse(response.isProductAdded());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }
}