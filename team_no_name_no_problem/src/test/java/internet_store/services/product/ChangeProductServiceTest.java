package internet_store.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.ChangeProductService;
import internet_store.core.services.product.ChangeProductValidator;
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
    public void incorrectIdForChangingDescription() {

        Product laptop = new Product("Laptop","Samsung",400);
        ChangeProductRequest request1 = new ChangeProductRequest(3L,"Laptop",
                                                                "Apple",400);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"database");
    }

    @Test
    public void correctIdForChangingDescription() {

        Product laptop = new Product("Laptop","Samsung",400);
        laptop.setId(5L);
        ChangeProductRequest request1 = new ChangeProductRequest(5L,"Laptop",
                "Apple",400);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),false);
        assertEquals(response.getErrors().size(),0);
        assertTrue(laptop.getDescription().equals("Apple"));
    }


    @Test
    public void incorrectIdForChangingTitle() {

        Product laptop = new Product("Laptop","Samsung",400);
        laptop.setId(6L);
        ChangeProductRequest request1 = new ChangeProductRequest(3L,"Notebook",
                                                                "Samsung",400);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"database");
    }

    @Test
    public void correctIdForChangingTitle() {

        Product laptop = new Product("Laptop","Samsung",400);
        laptop.setId(5L);
        ChangeProductRequest request1 = new ChangeProductRequest(5L,"Notebook",
                "Samsung",400);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),false);
        assertEquals(response.getErrors().size(),0);
        assertTrue(laptop.getTitle().equals("Notebook"));
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

        Product laptop = new Product("Notebook","Acer",400);
        laptop.setId(5L);
        ChangeProductRequest request1 = new ChangeProductRequest(5L,"Notebook",
                "Acer",250);
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(errors1);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),false);
        assertEquals(response.getErrors().size(),0);
        assertTrue(laptop.getPrice() == 250);
    }

}
