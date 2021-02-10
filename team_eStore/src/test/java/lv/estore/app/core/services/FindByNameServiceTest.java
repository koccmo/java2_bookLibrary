package lv.estore.app.core.services;

import lv.estore.app.core.database.InMemoryDatabaseImpl;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.FindByNameResponse;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class FindByNameServiceTest {

    @Mock
    NameValidator validator;
    @Mock
    InMemoryDatabaseImpl database;

    @InjectMocks
    FindByNameService service;

    @Test
    public void testFindByNameSuccess(){
        NameRequest request = new NameRequest("name");
        List<Product> products = new ArrayList<>();
        Product product = new Product("name",
                "description",
                new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR));
        products.add(product);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findByName(any())).thenReturn(products);
        FindByNameResponse response = service.execute(request);

        assertTrue(response.hasProducts());
        assertFalse(response.hasErrors());
        assertTrue(response.getProducts().stream().anyMatch(p -> "name".equals(p.getName())));
        assertTrue(response.getProducts().stream().anyMatch(p -> "description".equals(p.getDescription())));

        Mockito.verify(database).findByName(
                argThat(new NameMatcher("name")));
    }

    @Test
    public void testFindByNameError(){
        NameRequest request = new NameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        FindByNameResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors()
                .stream().anyMatch(s -> s.getField().equals("Name")
                        && s.getMessage().equals("Field should not be empty")));
        assertFalse(response.hasProducts());

        Mockito.verifyNoInteractions(database);
    }
}