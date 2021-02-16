package lv.estore.app.core.services;

import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import lv.estore.app.core.services.products.FindProductsByNameService;
import lv.estore.app.core.validators.products.ProductNameValidator;
import lv.estore.app.matchers.NameMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static lv.estore.app.utils.CommonUtils.getBigDecimal;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class FindProductsByNameServiceTest {

    @Mock
    ProductNameValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;

    @InjectMocks
    FindProductsByNameService service;

    @Test
    public void testFindByNameSuccess(){
        ProductNameRequest request = new ProductNameRequest("name");
        List<Product> products = new ArrayList<>();
        Product product = new Product("name",
                "description",
                getBigDecimal("1.0"));
        products.add(product);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findProductsByName(any())).thenReturn(products);
        GetProductsResponse response = service.execute(request);

        assertTrue(response.hasProducts());
        assertFalse(response.hasErrors());
        assertTrue(response.getProducts().stream().anyMatch(p -> "name".equals(p.getName())));
        assertTrue(response.getProducts().stream().anyMatch(p -> "description".equals(p.getDescription())));

        Mockito.verify(database).findProductsByName(
                argThat(new NameMatcher("name")));
    }

    @Test
    public void testFindByNameError(){
        ProductNameRequest request = new ProductNameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        GetProductsResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors()
                .stream().anyMatch(s -> s.getField().equals("Name")
                        && s.getMessage().equals("Field should not be empty")));
        assertFalse(response.hasProducts());

        Mockito.verifyNoInteractions(database);
    }
}