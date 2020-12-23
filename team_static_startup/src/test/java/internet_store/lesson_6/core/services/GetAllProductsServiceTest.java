package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.domain.Product;
import internet_store.lesson_6.core.requests.GetAllProductsRequest;
import internet_store.lesson_6.core.responses.GetAllProductsResponse;
import internet_store.lesson_6.core.services.GetAllProductsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllProductsServiceTest {

    @Mock private Database database;
    @InjectMocks private GetAllProductsService service;

    @Test
    public void shouldGetProductsFromDb() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("TV", "SONY", new BigDecimal("1000")));
        Mockito.when(database.getProductList()).thenReturn(products);
        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProductList().size(), 1);
        assertEquals(response.getProductList().get(0).getName(), "TV");
        assertEquals(response.getProductList().get(0).getDescription(), "SONY");
    }

}