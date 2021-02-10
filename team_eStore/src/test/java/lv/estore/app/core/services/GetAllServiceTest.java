package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.database.InMemoryDatabaseImpl;
import lv.estore.app.core.request.GetAllRequest;
import lv.estore.app.core.responses.GetAllResponse;
import lv.estore.app.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;

@RunWith(MockitoJUnitRunner.class)
public class GetAllServiceTest {

    CommonUtils commonUtils = new CommonUtils();

    @Mock
    InMemoryDatabaseImpl database;

    @InjectMocks
    GetAllService service;

    @Test
    public void testGetAllSuccess(){
        GetAllRequest request = new GetAllRequest();
        Product product1 = new Product("mouse",
                "wired", commonUtils.createBigDecimal("10.0"));
        Product product2 = new Product("keyboard",
                "wireless", commonUtils.createBigDecimal("20.0"));
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Mockito.when(database.getAllProducts()).thenReturn(products);
        GetAllResponse response = service.execute(request);

        assertTrue(response.getProducts().size() == 2);
        assertTrue(response.getProducts()
                .stream().anyMatch(s-> s.getName().equals("keyboard") && s.getDescription().equals("wireless")));
        assertFalse(response.hasErrors());

        Mockito.verify(database, atLeastOnce()).getAllProducts();
    }
}