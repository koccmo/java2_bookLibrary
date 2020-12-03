package internet_store.services.product;
/*
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.ChangeProductService;
import internet_store.core.services.product.ChangeProductValidator;
import internet_store.core.services.product.SearchProductRequestValidator;
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

    @Test
    public void databaseDoesNotContainsId() {

        SearchProductRequest request1 = new SearchProductRequest("Mobile phone","Nokia",
                "Title","ascending");
        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database","There is no such product with this ID!"));
        Mockito.when(changeProductValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(3L)).thenReturn(false);

        ChangeProductResponse response = changeProductService.execute(request1);
        assertEquals(response.hasErrors(),true);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"database");
    }
*/
