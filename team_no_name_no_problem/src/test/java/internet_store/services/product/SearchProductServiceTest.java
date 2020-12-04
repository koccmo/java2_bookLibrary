package internet_store.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.core.services.product.SearchProductRequestValidator;
import internet_store.core.services.product.SearchProductService;
import internet_store.database.product.ProductDatabase;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)

public class SearchProductServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private SearchProductRequestValidator searchProductRequestValidator;
    @InjectMocks
    SearchProductService searchProductService;

    private Ordering ordering = new Ordering("title", "Ascending");
    private Paging paging = new Paging(1, 2);

    @Test
    public void notValidSearch() {

        SearchProductRequest request = new SearchProductRequest("", "",
                                                                    ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("search", "Invalid search"));

        Mockito.when(searchProductRequestValidator.validate(request)).thenReturn(errors);

        SearchProductResponse response = searchProductService.execute(request);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "search");
    }

    @Test
    public void databaseDoesNotContainsSuchProductTitleAndDescription() {

        SearchProductRequest request1 = new SearchProductRequest("Mobile phone", "Nokia",
                ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("database", "There is no such product title"));

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitleAndDescription(request1.getTitle(),
                                     request1.getDescription())).thenReturn(new ArrayList<>());

        SearchProductResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "database");
    }
/*
    @Test
    public void databaseContainsSuchProductTitleAndDescription() {

        Product mobilePhone = new Product("Mobile phone","Nokia",40);
        List<Product> products = new ArrayList<>();
        products.add(mobilePhone);
        SearchProductRequest request1 = new SearchProductRequest("Mobile phone", "Nokia",
                                                                       ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitleAndDescription(request1.getTitle(),
                                     request1.getDescription())).thenReturn(new ArrayList<>());

        SearchProductResponse response = searchProductService.execute(request1);
        assertTrue(response.getProducts().contains(mobilePhone));
       // assertTrue(response.getProducts().size() == 1);
        //assertFalse(response.getProducts().size() == 0);
    }
*/
    @Test
    public void databaseContainsSuchProductTitle() {

        Product pen = new Product("Pen","Parker",35);
        List<Product> products = new ArrayList<>();
        products.add(pen);
        SearchProductRequest request = new SearchProductRequest("Pen", null,
                                                                      ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitle(request.getTitle())).thenReturn(products);

        SearchProductResponse response = searchProductService.execute(request);
        assertTrue(response.getProducts().contains(pen));
        assertTrue(response.getProducts().size() == 1);
        assertFalse(response.getProducts().size() == 0);
    }

    @Test
    public void databaseContainsSuchProductDescription() {

        Product pen = new Product("Pen","Parker",35);
        List<Product> products = new ArrayList<>();
        products.add(pen);
        SearchProductRequest request = new SearchProductRequest(null, "Parker",
                ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByDescription(request.getDescription())).thenReturn(products);

        SearchProductResponse response = searchProductService.execute(request);
        assertTrue(response.getProducts().contains(pen));
        assertTrue(response.getProducts().size() == 1);
        assertFalse(response.getProducts().size() == 0);
    }

}
