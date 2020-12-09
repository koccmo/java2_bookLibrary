package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.ChangeProductService;
import internet_store.core.services.product.validators.ChangeProductValidator;
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

public class ChangeProductServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private ChangeProductValidator changeProductValidator;
    @InjectMocks
    ChangeProductService changeProductService;

    @Test
    public void notValidId() {

        ChangeProductRequest request1 = new ChangeProductRequest(null,"Laptop",
                "Apple",400);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("id", "Not valid input for id"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"id");
    }

    @Test
    public void databaseDoesNotContainsId() {

        ChangeProductRequest request1 = new ChangeProductRequest(3L,"Laptop",
                                                                "Apple",400);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(3L)).thenReturn(false);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"database");
    }

    @Test
    public void correctIdForChangingDescription() {

        ChangeProductRequest request1 = new ChangeProductRequest(5L,"Laptop",
                "Apple",400);
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(5L)).thenReturn(true);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),false);
        assertEquals(response.getId().equals(5L),true);
    }


    @Test
    public void notValidPrice() {

        ChangeProductRequest request1 = new ChangeProductRequest(3L,"Notebook",
                                                                "Samsung",-1);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("price","Not valid input for price!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"price");
    }

    @Test
    public void correctIdForChangingTitle() {

        Product laptop = new Product("Laptop","Samsung",400);
        laptop.setId(5L);
        ChangeProductRequest request1 = new ChangeProductRequest(5L,"Notebook",
                                                                "Samsung",400);
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(5L)).thenReturn(true);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),false);
        assertTrue(response.getId().equals(5L));
    }

    @Test
    public void incorrectIdForChangingPrice() {

        Product laptop = new Product("Laptop","Samsung",400);
        laptop.setId(2L);
        ChangeProductRequest request1 = new ChangeProductRequest(3L,"Laptop",
                "Samsung",650);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"database");
    }

    @Test
    public void correctIdForChangingPrice() {

        ChangeProductRequest request1 = new ChangeProductRequest(5L,"Notebook",
                "Acer",250);
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(5L)).thenReturn(true);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),false);
        assertTrue(response.getId().equals(5L));
    }
}
