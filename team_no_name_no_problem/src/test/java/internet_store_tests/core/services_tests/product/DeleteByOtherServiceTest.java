package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.services.product.DeleteByOtherService;
import internet_store.core.services.product.validators.DeleteByOtherRequestValidator;
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

public class DeleteByOtherServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private DeleteByOtherRequestValidator deleteByOtherRequestValidator;
    @InjectMocks
    DeleteByOtherService deleteByOtherService;

    @Test
    public void notValidRequestToDeleteTest() {

        DeleteProductByOtherRequest deleteProductByOtherRequest = new DeleteProductByOtherRequest("", "",
                null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("delete", "Not valid input for delete"));

        Mockito.when(deleteByOtherRequestValidator.validate(deleteProductByOtherRequest)).thenReturn(errors);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(deleteProductByOtherRequest);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "delete");
    }

    @Test
    public void noTitleInDatabaseToDeleteProducTest() {

        DeleteProductByOtherRequest firstRequest = new DeleteProductByOtherRequest("Apple","",
                null,null);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database","database doesn't contain product with title Apple");
        errors.add(expectedError);

        Mockito.when(deleteByOtherRequestValidator.validate(firstRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsTitle("Apple")).thenReturn(false);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(firstRequest);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
    }

    @Test
    public void titleIsInDatabaseToDeleteProduct() {

        Product apple = new Product("Apple","Green",3);
        List<Product> products = new ArrayList<>();
        products.add(apple);
        List<CoreError> errors = new ArrayList<>();
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest("Apple","",
                null,null);

        Mockito.when(deleteByOtherRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.deleteAllByTitle(request.getTitle())).thenReturn(true);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(request);
        assertTrue(!productDatabase.containsTitle("Apple"));
    }

    @Test
    public void noDescriptionInDatabaseToDeleteTest() {

        DeleteProductByOtherRequest firstRequest = new DeleteProductByOtherRequest("","Red",
                null,null);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database","database doesn't contain product with description Red");
        errors.add(expectedError);

        Mockito.when(deleteByOtherRequestValidator.validate(firstRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.deleteAllByDescription(firstRequest.getDescription())).thenReturn(true);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(firstRequest);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
    }

    @Test
    public void descriptionIsInDatabaseToDeleteProductTest() {

        Product apple = new Product("Apple","Green",3);
        List<Product> products = new ArrayList<>();
        products.add(apple);
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest("","Green",
                null,null);

        Mockito.when(deleteByOtherRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.deleteAllByDescription(request.getDescription())).thenReturn(true);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(request);
        assertFalse(productDatabase.containsTitle("Green"));
        assertTrue(!productDatabase.containsProduct(apple));
    }

    @Test
    public void noSuchPriceInDatabaseToDeleteProductTest() {

        DeleteProductByOtherRequest firstRequest = new DeleteProductByOtherRequest("","",
                1,3);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database","database doesn't contain product with price 2");
        errors.add(expectedError);

        Mockito.when(deleteByOtherRequestValidator.validate(firstRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.deleteAllByPriceRange(firstRequest.getStartPrice(),firstRequest.getEndPrice())).thenReturn(true);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(firstRequest);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
    }

    @Test
    public void priceIsInDatabaseToDeleteProductTest() {

        Product apple = new Product("Apple","Green",3);
        List<Product> products = new ArrayList<>();
        products.add(apple);
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest("","",
                2,4);

        Mockito.when(deleteByOtherRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.deleteAllByPriceRange(request.getStartPrice(), request.getEndPrice())).thenReturn(true);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(request);
        assertFalse(productDatabase.containsPrice(3));
        assertTrue(!productDatabase.containsPrice(3));
    }

    @Test
    public void noTitleAndDescriptionInDatabaseTest() {

        DeleteProductByOtherRequest firstRequest = new DeleteProductByOtherRequest("Apple","Green",
            null,null);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database","database doesn't contain product with title Apple" +
                "and description green");
        errors.add(expectedError);

        Mockito.when(deleteByOtherRequestValidator.validate(firstRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsTitleAndDescription("Apple","Green")).thenReturn(false);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(firstRequest);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
    }

    @Test
    public void titleAndDescriptionAreInDatabaseToBeDeleted() {

        Product apple = new Product("Apple","Green",3);
        List<Product> products = new ArrayList<>();
        products.add(apple);
        List<CoreError> errors = new ArrayList<>();
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest("Apple","Green",
                null,null);

        Mockito.when(deleteByOtherRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.deleteAllByTitleAndDescription(request.getTitle(),request.getDescription())).thenReturn(true);

        DeleteByOtherResponse response = (DeleteByOtherResponse) deleteByOtherService.execute(request);
        assertTrue(productDatabase.containsTitleAndDescription("Apple","Green"));

    }
}
