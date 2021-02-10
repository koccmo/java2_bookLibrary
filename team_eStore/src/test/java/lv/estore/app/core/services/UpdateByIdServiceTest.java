package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.InMemoryProductRepositoryImpl;
import lv.estore.app.core.request.UpdateRequest;
import lv.estore.app.core.responses.UpdateResponse;
import lv.estore.app.core.validators.UpdateValidator;
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
public class UpdateByIdServiceTest {

    @Mock
    UpdateValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;
    @Mock
    CommonUtils utils;

    @InjectMocks
    UpdateByIdService service;

    @Test
    public void testUpdateProductSuccess(){
        BigDecimal price = new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(utils.createBigDecimal(any())).thenReturn(price);
        Mockito.when(database.updateById(any(), any(), any(), any())).thenReturn(true);
        UpdateRequest request = new UpdateRequest("1", "name", "description", "1.0");
        UpdateResponse response = service.execute(request);

        assertTrue(response.isProductUpdated());
        assertFalse(response.hasErrors());

        Mockito.verify(database, Mockito.atLeastOnce())
                .updateById(1L, "name", "description", price);
    }

    @Test
    public void testUpdateProductError(){
        UpdateRequest request = new UpdateRequest(null, "description", "1.0", "1.0");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        UpdateResponse response = service.execute(request);

        assertFalse(response.isProductUpdated());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }
}