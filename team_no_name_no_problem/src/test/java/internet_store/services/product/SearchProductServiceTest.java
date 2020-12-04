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

    private Ordering ordering = new Ordering("title", "Ascending");
    private Paging paging = new Paging(1, 2);

    @Test
    public void notValidSearch() {

        SearchProductRequest request1 = new SearchProductRequest("", "",
                                                                    ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("search", "Invalid search"));

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(errors);

        SearchProductResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "search");
    }

    @Test
    public void databaseDoesNotContainsSuchProductTitle() {

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
}
