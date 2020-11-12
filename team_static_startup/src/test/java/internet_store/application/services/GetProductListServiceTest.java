package internet_store.application.services;

import internet_store.application.database.Database;
import internet_store.application.database.InMemoryDatabase;
import internet_store.application.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

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
        assertEquals(database.getProductList(), service.getProductList());
    }

    @Test
    public void shouldReturnProductList_whenItIsEmpty() {
        GetProductListService service = new GetProductListService(database);
        assertEquals(database.getProductList(), service.getProductList());
    }
}