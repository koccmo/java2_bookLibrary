package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.InMemoryProductRepositoryImpl;
import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.responses.FindByIdResponse;
import lv.estore.app.core.validators.IdValidator;
import lv.estore.app.matchers.IdMatcher;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class FindByIdServiceTest {

    @Mock
    IdValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;

    @InjectMocks
    FindByIdService service;

    @Test
    public void testFindByIdSuccess(){
        IdRequest request = new IdRequest("1");
        Product product = new Product("name",
                "description",
                new BigDecimal(1.0).abs().setScale(2, RoundingMode.FLOOR));

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findById(any())).thenReturn(product);
        FindByIdResponse response = service.execute(request);

        assertTrue(response.hasProduct());
        assertFalse(response.hasErrors());
        assertEquals("name", response.getProduct().getName());
        assertEquals("description", response.getProduct().getDescription());

        Mockito.verify(database).findById(
                argThat(new IdMatcher(1L)));
    }

    @Test
    public void testFindByIdError(){
        IdRequest request = new IdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Id", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        FindByIdResponse response = service.execute(request);

        assertFalse(response.hasProduct());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }
}