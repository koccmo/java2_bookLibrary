package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.DeleteProductByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteProductByIdResponse;
import internet_store.core.services.product.DeleteProductByIdService;
import internet_store.core.services.product.validators.DeleteProductByIdRequestValidator;
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

public class DeleteByIdServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private DeleteProductByIdRequestValidator deleteProductRequestValidator;
    @InjectMocks
    DeleteProductByIdService deleteByIdService;

    @Test
    public void deleteNotValidRequest() {

        DeleteProductByIdRequest request1 = new DeleteProductByIdRequest(-2L);

        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("id", "Not valid input for id"));

        Mockito.when(deleteProductRequestValidator.validate(request1)).thenReturn(errors1);

        DeleteProductByIdResponse response = deleteByIdService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
    }

    @Test
    public void testNoIdInDatabase() {

        DeleteProductByIdRequest request1 = new DeleteProductByIdRequest(2L);

        List<CoreError> errors1 = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "database doesn't contain product with id 2");
        errors1.add(expectedError);

        Mockito.when(deleteProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(2L)).thenReturn(false);

        DeleteProductByIdResponse response = deleteByIdService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().contains(expectedError), true);
    }

    @Test
    public void testDeletedSuccessfully() {

        DeleteProductByIdRequest request1 = new DeleteProductByIdRequest(2L);
        Product product = new Product("Title", "D", 5);
        product.setId(2L);
        List<Product>products = new ArrayList<>();
        products.add(product);

        Mockito.when(deleteProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(2L)).thenReturn(true);
        Mockito.when(productDatabase.getProducts()).thenReturn(products);

        DeleteProductByIdResponse response = deleteByIdService.execute(request1);
        assertFalse(response.hasErrors());
        assertTrue(response.getId().equals(2L));
    }

}