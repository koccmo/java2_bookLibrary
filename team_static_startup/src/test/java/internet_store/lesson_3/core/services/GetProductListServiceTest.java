package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.responses.PrintProductsToConsoleResponse;
import internet_store.lesson_3.core.services.GetProductListService;
import internet_store.lesson_3.database.Database;
import internet_store.lesson_3.database.InMemoryDatabase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GetProductListServiceTest {

    Database database;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
    }

    @Test
    public void shouldReturnProductList() {
        database.add(new Product("iphone12", "mobile phone", new BigDecimal("900.00")));
        database.add(new Product("imac", "pc", new BigDecimal("4000.00")));
        GetProductListService service = new GetProductListService(database);
        PrintProductsToConsoleResponse response = service.getProductList();

        assertEquals(database.getProductList(), response.getProductList());
    }

    @Test
    public void shouldReturnProductList_whenItIsEmpty() {
        GetProductListService service = new GetProductListService(database);
        PrintProductsToConsoleResponse response = service.getProductList();
        assertEquals(database.getProductList(), response.getProductList());
    }
}