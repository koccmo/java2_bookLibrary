package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.services.product.validators.AddProductRequestValidator;
import internet_store.core.services.product.AddProductService;
import internet_store.database.product.ProductDatabase;
import internet_store_tests.core.services_tests.matchers.ProductMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)

public class AddProductServiceTest {

    @Mock private ProductDatabase productDatabase;
    @Mock private AddProductRequestValidator addProductRequestValidator;
    @InjectMocks AddProductService addProductService;

    @Test
    public void noTitleAddedToProductRequestTest() {

        Product laptop = new Product(null,"Samsung",400);
        AddProductRequest request1 = new AddProductRequest(laptop);

        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("title","Should not be empty!"));
        Mockito.when(addProductRequestValidator.validate(request1)).thenReturn(errors1);

        AddProductResponse response = addProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"title");

        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    public void noDescriptionAddedToProductRequestTest() {

        Product laptop = new Product("Laptop",null,400);
        AddProductRequest request1 = new AddProductRequest(laptop);

        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("description","Should not be empty!"));
        Mockito.when(addProductRequestValidator.validate(request1)).thenReturn(errors1);

        AddProductResponse response = addProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"description");

        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    public void noPriceAddedToProductRequestTest() {

        Product laptop = new Product("Laptop","Samsung",0);
        AddProductRequest request1 = new AddProductRequest(laptop);

        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("price","Should not be empty!"));
        Mockito.when(addProductRequestValidator.validate(request1)).thenReturn(errors1);

        AddProductResponse response = addProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"price");

        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    public void testDatabaseContainsTheSameProduct() {

        Product laptop = new Product("Laptop","Samsung",5);
        AddProductRequest request1 = new AddProductRequest(laptop);

        List<CoreError> errors1 = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database contains the same product");
        errors1.add(expectedError);
        Mockito.when(addProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsProduct(request1.getProduct())).thenReturn(true);

        AddProductResponse response = addProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertTrue(response.getErrors().contains(expectedError));
    }

    @Test
    public void testSuccessfullyAdded() {

        Product laptop = new Product("Laptop","Samsung",5);
        AddProductRequest request1 = new AddProductRequest(laptop);

        Mockito.when(addProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsProduct(request1.getProduct())).thenReturn(false);

        AddProductResponse response = addProductService.execute(request1);
        assertFalse(response.hasErrors());

        Mockito.verify(productDatabase).add(argThat(new ProductMatcher("Laptop", "Samsung", 5)));
    }
}