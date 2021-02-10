package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.InMemoryProductRepositoryImpl;
import lv.estore.app.core.request.SearchRequest;
import lv.estore.app.core.responses.SearchResponse;
import lv.estore.app.core.validators.SearchValidator;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

    @Mock
    SearchValidator validator;
    @Mock
    InMemoryProductRepositoryImpl database;
    @Mock
    CommonUtils utils;

    @InjectMocks
    SearchService service;

    @Test
    public void testSearchProducts_FindManyByNameAndPrice_Success(){
        BigDecimal price = new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(utils.createBigDecimal(any())).thenReturn(price);
        Mockito.when(database.findManyByNameAndPrice(any(), any()))
                .thenReturn(Arrays.asList(new Product("name1","description1",price),
                        new Product("name2", "description2", price)));
        SearchRequest request = new SearchRequest("name", "1.0");
        SearchResponse response = service.execute(request);

        assertEquals(2, response.getProducts().size());
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).findManyByNameAndPrice("name", price);
    }

    @Test
    public void testSearchProducts_FindManyByPrice_Success(){
        BigDecimal price = new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(utils.createBigDecimal(any())).thenReturn(price);
        Mockito.when(database.findByPrice(any()))
                .thenReturn(Arrays.asList(new Product("name1","description1",price),
                        new Product("name2", "description2", price)));
        SearchRequest request = new SearchRequest(null, "1.0");
        SearchResponse response = service.execute(request);

        assertEquals(2, response.getProducts().size());
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).findByPrice(price);
    }

    @Test
    public void testSearchProducts_FindManyByName_Success(){
        BigDecimal price = new BigDecimal("1.0").abs().setScale(2, RoundingMode.FLOOR);

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.findByName(any()))
                .thenReturn(Arrays.asList(new Product("name1","description1",price),
                        new Product("name2", "description2", price)));
        SearchRequest request = new SearchRequest("name", null);
        SearchResponse response = service.execute(request);

        assertEquals(2, response.getProducts().size());
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).findByName("name");
    }

    @Test
    public void testSearchProductsError(){
        SearchRequest request = new SearchRequest(null, "1.0");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Field should not be empty"));

        Mockito.when(validator.validate(any())).thenReturn(errors);
        SearchResponse response = service.execute(request);

        assertNull(response.getProducts());
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(database);
    }

}