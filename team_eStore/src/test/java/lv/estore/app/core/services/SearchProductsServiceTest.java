package lv.estore.app.core.services;

import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.SearchProductByNamePriceRequest;
import lv.estore.app.core.responses.products.SearchProductResponse;
import lv.estore.app.core.services.products.SearchProductsService;
import lv.estore.app.core.validators.products.ProductSearchValidator;
import lv.estore.app.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lv.estore.app.utils.CommonUtils.getBigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;

@RunWith(MockitoJUnitRunner.class)
public class SearchProductsServiceTest {

    @Mock
    ProductSearchValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;

    @Mock
    CommonUtils utils;

    @InjectMocks
    SearchProductsService service;

    @Test
    public void testSearchProducts_FindManyByNameAndPrice_Success(){
        BigDecimal price = getBigDecimal("1.0");

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findProductsByNameAndPrice(any(), any()))
                .thenReturn(Arrays.asList(new Product("name1","description1", price),
                        new Product("name2", "description2", price)));
        SearchProductByNamePriceRequest request = new SearchProductByNamePriceRequest("name", "1.0");
        SearchProductResponse response = service.execute(request);

        assertEquals(2, response.getProducts().size());
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).findProductsByNameAndPrice("name", price);
    }

    @Test
    public void testSearchProducts_FindManyByPrice_Success(){
        BigDecimal price = getBigDecimal("1.0");

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findProductsByPrice(any()))
                .thenReturn(Arrays.asList(new Product("name1","description1", price),
                        new Product("name2", "description2", price)));
        SearchProductByNamePriceRequest request = new SearchProductByNamePriceRequest(null, "1.0");
        SearchProductResponse response = service.execute(request);

        assertEquals(2, response.getProducts().size());
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).findProductsByPrice(price);
    }

    @Test
    public void testSearchProducts_FindManyByName_Success(){
        BigDecimal price = getBigDecimal("1.0");

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findProductsByName(any()))
                .thenReturn(Arrays.asList(new Product("name1","description1", price),
                        new Product("name2", "description2", price)));
        SearchProductByNamePriceRequest request = new SearchProductByNamePriceRequest("name", null);
        SearchProductResponse response = service.execute(request);

        assertEquals(2, response.getProducts().size());
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).findProductsByName("name");
    }

    @Test
    public void testSearchProductsError(){
        SearchProductByNamePriceRequest request = new SearchProductByNamePriceRequest(null, "1.0");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        SearchProductResponse response = service.execute(request);

        assertNull(response.getProducts());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }

}