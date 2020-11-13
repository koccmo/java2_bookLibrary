package internet_store.application.core.services;

import internet_store.application.database.Database;
import internet_store.application.database.InMemoryDatabase;
import internet_store.application.core.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChangeProductNameServiceTest {

    Database database;
    ChangeProductNameService service;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        service = new ChangeProductNameService(database);
    }

    @Test
    public void shouldChangeProductName() {
        database.add(new Product("iphone", "phone",new BigDecimal("900.00")));
        database.add(new Product("imac", "pc",new BigDecimal("4000.00")));
        boolean productFound = service.changeProductName(2L, "iphone12");
        assertTrue(productFound);
        assertEquals("iphone12", database.getProductList().get(1).getName());
    }

    @Test
    public void shouldChangeProductName_whenNewNameIsCompound() {
        database.add(new Product("TV", "SONY",new BigDecimal("900.00")));
        database.add(new Product("TV", "SAMSUNG",new BigDecimal("1000.00")));
        boolean productFound = service.changeProductName(2L, "TV Set");
        assertTrue(productFound);
        assertEquals("TV Set", database.getProductList().get(1).getName());
    }

    @Test
    public void shouldNotChangeProductName_whenIdNotFound() {
        database.add(new Product("TV", "SONY",new BigDecimal("900.00")));
        database.add(new Product("TV", "SAMSUNG",new BigDecimal("1000.00")));
        boolean productFound = service.changeProductName(5L, "TV Set");
        assertFalse(productFound);
    }
}