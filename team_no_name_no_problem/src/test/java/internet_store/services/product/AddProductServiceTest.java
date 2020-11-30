package internet_store.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.services.product.AddProductRequestValidator;
import internet_store.core.services.product.AddProductService;
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
    }
}