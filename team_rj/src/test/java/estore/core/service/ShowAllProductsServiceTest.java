package estore.core.service;

import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;
import estore.database.ProductDB;
import estore.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllProductsServiceTest {

    @Mock
    private ProductDB database;

    @InjectMocks
    private ShowAllProductsService service;

    @Test
    public void shouldReturnAllProductsFromDatabase() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product_1", "Good product 1", "Category"));
        products.add(new Product("Product_2", "Good product 2", "Category"));
        Mockito.when(database.getDatabase()).thenReturn(products);

        ShowAllProductsRequest request = new ShowAllProductsRequest();
        ShowAllProductsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 2);
        assertEquals(response.getProducts().get(0).getName(), "Product_1");
        assertEquals(response.getProducts().get(1).getDescription(), "Good product 2");
    }

}