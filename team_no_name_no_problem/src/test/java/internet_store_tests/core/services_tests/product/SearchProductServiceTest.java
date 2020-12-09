package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.core.services.product.validators.SearchProductRequestValidator;
import internet_store.core.services.product.SearchProductService;
import internet_store.database.product.ProductDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

    private Ordering ordering = new Ordering("title", "ASC");
    private Paging paging = new Paging(1, 2);

    @Test
    public void notValidSearch() {

        SearchProductRequest request = new SearchProductRequest("", "",
                                                                    ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("search", "Not valid input for search"));

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
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain product with title: Mobile phone and description: Nokia");
        errors.add(expectedError);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitleAndDescription(request1.getTitle(),
                                     request1.getDescription())).thenReturn(new ArrayList<>());

        SearchProductResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertTrue(response.getErrors().contains(expectedError));
    }

    @Test
    public void databaseContainsSuchProductTitleAndDescription() {

        Product mobilePhone = new Product("Mobile phone","Nokia",40);
        List<Product> products = new ArrayList<>();
        products.add(mobilePhone);
        SearchProductRequest request1 = new SearchProductRequest("Mobile phone", "Nokia",
                                                                       ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitleAndDescription(request1.getTitle(),
                                     request1.getDescription())).thenReturn(products);

        SearchProductResponse response = searchProductService.execute(request1);
        assertTrue(response.getProducts().contains(mobilePhone));
        assertTrue(response.getProducts().size() == 1);
    }

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
    }

    @Test
    public void databaseDoesNotContainsSuchProductDescription() {

        SearchProductRequest request1 = new SearchProductRequest("", "Nokia",
                ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database doesn't contain products wits description: Nokia");
        errors.add(expectedError);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByDescription(request1.getDescription())).thenReturn(new ArrayList<>());

        SearchProductResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertTrue(response.getErrors().contains(expectedError));
    }

    @Test
    public void databaseDoesNotContainsSuchProductTitle() {

        SearchProductRequest request1 = new SearchProductRequest("Mobile phone", "",
                ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database doesn't contain products wits title: Mobile phone");
        errors.add(expectedError);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());

        SearchProductResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertTrue(response.getErrors().contains(expectedError));
    }

    @Test
    public void ascendingOrdering() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductRequest searchProductRequest = new SearchProductRequest("Mobile phone",
                null, new Ordering("description","ASC"), paging);

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().get(0).getDescription(),"Sony");
        assertEquals(response.getProducts().get(1).getDescription(),"SonyEricson");
    }

    @Test
    public void descendingOrdering() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductRequest searchProductRequest = new SearchProductRequest("Mobile phone",
                null, new Ordering("description","DSC"), paging);

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().get(0).getDescription(),"SonyEricson");
        assertEquals(response.getProducts().get(1).getDescription(),"Sony");
    }

    @Test
    public void pagingSecondPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductRequest searchProductRequest = new SearchProductRequest("Mobile phone",
            null, new Ordering("description","ASC"), new Paging(2,1));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().size(),1);
        assertTrue(response.getProducts().get(0).getDescription().equals("SonyEricson"));
    }

    @Test
    public void pagingFirstPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductRequest searchProductRequest = new SearchProductRequest("Mobile phone",
                null, new Ordering("description","ASC"),
                          new Paging(1,2));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().size(),2);
        assertTrue(response.getProducts().get(0).getDescription().equals("Sony"));
    }

    @Test
    public void pagingNotFullPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        Product mobilePhone3 = new Product("Mobile phone","Good",4000);
        products.add(mobilePhone3);

        SearchProductRequest searchProductRequest = new SearchProductRequest("Mobile phone",
                null, new Ordering("description","ASC"),
                new Paging(2,2));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().size(),1);
        assertTrue(response.getProducts().get(0).getDescription().equals("SonyEricson"));
    }

    @Test
    public void pagingEmptyPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        Product mobilePhone3 = new Product("Mobile phone","Good",4000);
        products.add(mobilePhone3);

        SearchProductRequest searchProductRequest = new SearchProductRequest("Mobile phone",
                null, new Ordering("description","ASC"),
                new Paging(3,2));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.findAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertTrue(response.getProducts().isEmpty());
    }

}
