package lv.estore.app.core.services;

import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.FindProductByIdResponse;
import lv.estore.app.core.services.products.FindProductByIdService;
import lv.estore.app.core.validators.products.ProductIdValidator;
import lv.estore.app.matchers.IdMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static lv.estore.app.utils.CommonUtils.getBigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class FindProductByIdServiceTest {

    @Mock
    ProductIdValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;

    @InjectMocks
    FindProductByIdService service;

    @Test
    public void testFindByIdSuccess(){
        ProductIdRequest request = new ProductIdRequest("1");
        Product product = new Product("name",
                "description",
                getBigDecimal("1.0"));

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findProductById(any())).thenReturn(product);
        FindProductByIdResponse response = service.execute(request);

        assertTrue(response.hasProduct());
        assertFalse(response.hasErrors());
        assertEquals("name", response.getProduct().getName());
        assertEquals("description", response.getProduct().getDescription());

        Mockito.verify(database).findProductById(
                argThat(new IdMatcher(1L)));
    }

    @Test
    public void testFindByIdError(){
        ProductIdRequest request = new ProductIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Id", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        FindProductByIdResponse response = service.execute(request);

        assertFalse(response.hasProduct());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }
}