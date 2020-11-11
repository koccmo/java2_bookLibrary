package internet_store.application.services;
/*
import internet_store.application.database.Database;
import internet_store.application.database.InMemoryDatabase;
import internet_store.application.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FindProductServiceTest {
    FindProductService findProductService;
    Database database;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        findProductService = new FindProductService(database);
    }

    @Test
    public void shouldFindProductByProductName() {
        database.add(new Product("tv", "good tv", new BigDecimal("499.99")));
        database.add(new Product("tv", "good tv, good", new BigDecimal("399.99")));
        List<Product> testArray = findProductService.findByProductName("tv");
        assertEquals(2, testArray.size());
    }

    @Test
    public void findById() {
        Product productTV = new Product("tv", "good tv", new BigDecimal("499.99"));
        database.add(productTV);
        Product productToFind = findProductService.findById(1L).get();
        assertEquals(productTV, productToFind);
    }

}

 */