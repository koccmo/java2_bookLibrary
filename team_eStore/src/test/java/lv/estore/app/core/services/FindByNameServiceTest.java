package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.repository.ProductDatabase;
import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.FindResponse;
import lv.estore.app.core.validators.NameValidator;
import lv.estore.app.matchers.NameMatcher;
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
public class FindByNameServiceTest {

    @Mock
    NameValidator validator;
    @Mock
    ProductDatabase database;

    @InjectMocks
    FindByNameService service;

    @Test
    public void testFindByNameSuccess(){
        NameRequest request = new NameRequest("name");
        Product product = new Product("name",
                "description",
                new BigDecimal(1.0).abs().setScale(2, RoundingMode.FLOOR));

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findByName(any())).thenReturn(product);
        FindResponse response = service.execute(request);

        assertTrue(response.hasProduct());
        assertFalse(response.hasErrors());
        assertEquals("name", response.getProduct().getName());
        assertEquals("description", response.getProduct().getDescription());

        Mockito.verify(database).findByName(
                argThat(new NameMatcher("name")));
    }

    @Test
    public void testFindByNameError(){
        NameRequest request = new NameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        FindResponse response = service.execute(request);

        assertFalse(response.hasProduct());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }
}