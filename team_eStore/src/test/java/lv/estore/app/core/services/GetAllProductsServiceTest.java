package lv.estore.app.core.services;

import lv.estore.app.core.database.products.InMemoryProductRepositoryImpl;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.request.products.GetAllProductsRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import lv.estore.app.core.services.products.GetAllProductsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static lv.estore.app.utils.CommonUtils.getBigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;

@RunWith(MockitoJUnitRunner.class)
public class GetAllProductsServiceTest {

    @Mock
    InMemoryProductRepositoryImpl database;

    @InjectMocks
    GetAllProductsService service;

    @Test
    public void testGetAllSuccess(){
        GetAllProductsRequest request = new GetAllProductsRequest();
        Product product1 = new Product("mouse",
                "wired", getBigDecimal("10.0"));
        Product product2 = new Product("keyboard",
                "wireless", getBigDecimal("20.0"));
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Mockito.when(database.getAllProducts()).thenReturn(products);
        GetProductsResponse response = service.execute(request);

        assertEquals(2, response.getProducts().size());
        assertTrue(response.getProducts()
                .stream().anyMatch(s-> s.getName().equals("keyboard") && s.getDescription().equals("wireless")));
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).getAllProducts();
    }
}