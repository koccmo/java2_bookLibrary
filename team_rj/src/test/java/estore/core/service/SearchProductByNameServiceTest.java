package estore.core.service;

import estore.core.domain.Product;
import estore.core.domain.ProductCategory;
import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByNameValidator;
import estore.database.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchProductByNameServiceTest {

    @Mock
    private ProductRepository database;
    @Mock
    private SearchProductByNameValidator validator;

    @InjectMocks
    private SearchProductByNameService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        Paging paging = new Paging("2", null);
        SearchProductByNameRequest request = new SearchProductByNameRequest(null, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product Name", "Must not be empty!"));
        errors.add(new CoreError("Page Size", "Must be positive integer!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchProductByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(), "Product Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
        assertEquals(response.getErrors().get(1).getField(), "Page Size");
        assertEquals(response.getErrors().get(1).getMessage(), "Must be positive integer!");
    }

    @Test
    public void shouldReturnProductsIfNameIsProvided() {
        SearchProductByNameRequest request = new SearchProductByNameRequest("Product");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product", "Good product", new ProductCategory("Category")));
        Mockito.when(database.searchProductByName("Product")).thenReturn(products);
        SearchProductByNameResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProductsFound(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Product");
        assertEquals(response.getProducts().get(0).getDescription(), "Good product");
    }

    @Test
    public void shouldReturnProductsByNameAndOrderDescending() {
        Ordering ordering = new Ordering("Price", "desc");
        SearchProductByNameRequest request = new SearchProductByNameRequest("Product", ordering);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product", "Good product 1", new ProductCategory("Category")));
        products.add(new Product("Product", "Good product 2", new ProductCategory("Category")));
        products.get(0).setPrice(10);
        products.get(1).setPrice(20);
        Mockito.when(database.searchProductByName("Product")).thenReturn(products);
        SearchProductByNameResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProductsFound(), 2);
        assertEquals(response.getProducts().get(0).getDescription(), "Good product 2");
        assertEquals(response.getProducts().get(1).getDescription(), "Good product 1");
    }

    @Test
    public void shouldReturnProductsByNameAndPerformPaging() {
        Paging paging = new Paging("2", "1");
        SearchProductByNameRequest request = new SearchProductByNameRequest("Product", paging);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product", "Good product 1", new ProductCategory("Category")));
        products.add(new Product("Product", "Good product 2", new ProductCategory("Category")));
        Mockito.when(database.searchProductByName("Product")).thenReturn(products);
        SearchProductByNameResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProductsFound(), 1);
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getDescription(), "Good product 2");
    }

    @Test
    public void shouldReturnProductsByNameAndOrderAscendingAndPerformPaging() {
        Paging paging = new Paging("1", "2");
        Ordering ordering = new Ordering("Price", "asc");
        SearchProductByNameRequest request = new SearchProductByNameRequest("Product", ordering, paging);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product", "Good product 4", new ProductCategory("Category")));
        products.add(new Product("Product", "Good product 3", new ProductCategory("Category")));
        products.add(new Product("Product", "Good product 2", new ProductCategory("Category")));
        products.add(new Product("Product", "Good product 1", new ProductCategory("Category")));
        products.get(0).setPrice(30);
        products.get(1).setPrice(20);
        products.get(2).setPrice(40);
        products.get(3).setPrice(10);
        Mockito.when(database.searchProductByName("Product")).thenReturn(products);
        SearchProductByNameResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProductsFound(), 2);
        assertEquals(response.getProducts().get(0).getDescription(), "Good product 1");
        assertEquals(response.getProducts().get(1).getDescription(), "Good product 3");
    }
}