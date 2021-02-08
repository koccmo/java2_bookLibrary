package estore.core.service;

import estore.core.domain.Product;
import estore.core.domain.ProductCategory;
import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByCategoryRequest;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByCategoryValidator;
import estore.database.ProductCategoryRepository;
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
public class SearchProductByCategoryServiceTest {

    @Mock
    private ProductRepository database;
    @Mock
    private SearchProductByCategoryValidator validator;
    @Mock
    private ProductCategoryRepository categoryRepository;

    @InjectMocks
    private SearchProductByCategoryService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        Paging paging = new Paging("2", null);
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest(null, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product category", "Must not be empty!"));
        errors.add(new CoreError("Page Size", "Must be positive integer!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchProductByCategoryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(), "Product category");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
        assertEquals(response.getErrors().get(1).getField(), "Page Size");
        assertEquals(response.getErrors().get(1).getMessage(), "Must be positive integer!");
    }

    @Test
    public void shouldReturnProductsIfCategoryIsProvided() {
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category");
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product", "Good product", new ProductCategory("Category")));
        Mockito.when(database.searchProductByCategory(any())).thenReturn(products);
        SearchProductByCategoryResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProductsFound(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Product");
        assertEquals(response.getProducts().get(0).getDescription(), "Good product");
    }

    @Test
    public void shouldReturnProductsByCategoryAndOrderDescending() {
        Ordering ordering = new Ordering("Price", "desc");
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category", ordering);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product_1", "Good product 1", new ProductCategory("Category")));
        products.add(new Product("Product_2", "Good product 2", new ProductCategory("Category")));
        products.get(0).setPrice(10);
        products.get(1).setPrice(20);
        Mockito.when(database.searchProductByCategory(any())).thenReturn(products);
        SearchProductByCategoryResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProductsFound(), 2);
        assertEquals(response.getProducts().get(0).getName(), "Product_2");
        assertEquals(response.getProducts().get(1).getName(), "Product_1");
    }

    @Test
    public void shouldReturnProductsByCategoryAndPerformPaging() {
        Paging paging = new Paging("2", "1");
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category", paging);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product_1", "Good product 1", new ProductCategory("Category")));
        products.add(new Product("Product_2", "Good product 2", new ProductCategory("Category")));
        Mockito.when(database.searchProductByCategory(any())).thenReturn(products);
        SearchProductByCategoryResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProductsFound(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Product_2");
        assertEquals(response.getProducts().get(0).getDescription(), "Good product 2");
    }

    @Test
    public void shouldReturnProductsByCategoryAndOrderAscendingAndPerformPaging() {
        Paging paging = new Paging("1", "2");
        Ordering ordering = new Ordering("Name", "asc");
        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest("Category", ordering, paging);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product_3", "Good product 3", new ProductCategory("Category")));
        products.add(new Product("Product_2", "Good product 2", new ProductCategory("Category")));
        products.add(new Product("Product_1", "Good product 1", new ProductCategory("Category")));
        Mockito.when(database.searchProductByCategory(any())).thenReturn(products);
        SearchProductByCategoryResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getProductsFound(), 2);
        assertEquals(response.getProducts().get(0).getName(), "Product_1");
        assertEquals(response.getProducts().get(0).getDescription(), "Good product 1");
        assertEquals(response.getProducts().get(1).getName(), "Product_2");
        assertEquals(response.getProducts().get(1).getDescription(), "Good product 2");
    }
}