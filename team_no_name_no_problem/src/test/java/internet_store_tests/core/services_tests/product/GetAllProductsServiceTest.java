package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.GetAllProductsService;
import internet_store.core.services.product.validators.GetAllProductsValidator;
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

public class GetAllProductsServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private GetAllProductsValidator getAllProductsValidator;
    @InjectMocks
    GetAllProductsService getAllProductsService;

    @Test
    public void testEmptyDatabase() {

        GetProductsRequest request1 = new GetProductsRequest();

        List<CoreError> errors1 = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database is empty");
        errors1.add(expectedError);
        Mockito.when(getAllProductsValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.getProducts()).thenReturn(new ArrayList<>());

        GetProductsResponse response = getAllProductsService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertTrue(response.getErrors().contains(expectedError));
    }

    @Test
    public void testSuccessfullyReceivedList() {
        Product product = new Product("Title", "D", 5);
        List<Product> products = new ArrayList<>();
        products.add(product);
        GetProductsRequest request1 = new GetProductsRequest();

        Mockito.when(getAllProductsValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.getProducts()).thenReturn(products);

        GetProductsResponse response = getAllProductsService.execute(request1);
        assertFalse(response.hasErrors());
        assertTrue(response.getProducts().size() == 1);
        assertTrue(response.getProducts().equals(products));
    }
}
