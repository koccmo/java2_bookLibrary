package internet_store.lesson_5.core.services;

import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.Ordering;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderingProductsServiceTest {

    List<Product> database;
    OrderingProductsService orderingProductsService;

    @Before
    public void setUp() {
        orderingProductsService = new OrderingProductsService();
        database = new ArrayList<>();
        database.add(new Product("iphone11", "more deprecated", new BigDecimal("800.00")));
        database.add(new Product("iphone11 pro", "deprecated", new BigDecimal("850.00")));
        database.add(new Product("iphone12", "newer", new BigDecimal("900.00")));
        database.add(new Product("iphone12 pro", "newest", new BigDecimal("950.00")));
    }

    @Test
    public void shouldOrderByName_whenDirectionIsAscending() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("iphone11", "more deprecated", new BigDecimal("800.00")));
        expected.add(new Product("iphone11 pro", "deprecated", new BigDecimal("850.00")));
        expected.add(new Product("iphone12", "newer", new BigDecimal("900.00")));
        expected.add(new Product("iphone12 pro", "newest", new BigDecimal("950.00")));
        Ordering ordering = new Ordering("Name", "Ascending");
        assertEquals(expected, orderingProductsService.order(database, ordering));
    }

    @Test
    public void shouldOrderByName_whenDirectionIsDescending() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("iphone12 pro", "newest", new BigDecimal("950.00")));
        expected.add(new Product("iphone12", "newer", new BigDecimal("900.00")));
        expected.add(new Product("iphone11 pro", "deprecated", new BigDecimal("850.00")));
        expected.add(new Product("iphone11", "more deprecated", new BigDecimal("800.00")));
        Ordering ordering = new Ordering("Name", "Descending");
        assertEquals(expected, orderingProductsService.order(database, ordering));
    }

    @Test
    public void shouldOrderByDescription_whenDirectionIsAscending() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("iphone11 pro", "deprecated", new BigDecimal("850.00")));
        expected.add(new Product("iphone11", "more deprecated", new BigDecimal("800.00")));
        expected.add(new Product("iphone12", "newer", new BigDecimal("900.00")));
        expected.add(new Product("iphone12 pro", "newest", new BigDecimal("950.00")));
        Ordering ordering = new Ordering("Description", "Ascending");
        assertEquals(expected, orderingProductsService.order(database, ordering));
    }

    @Test
    public void shouldOrderByDescription_whenDirectionIsDescending() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("iphone12 pro", "newest", new BigDecimal("950.00")));
        expected.add(new Product("iphone12", "newer", new BigDecimal("900.00")));
        expected.add(new Product("iphone11", "more deprecated", new BigDecimal("800.00")));
        expected.add(new Product("iphone11 pro", "deprecated", new BigDecimal("850.00")));
        Ordering ordering = new Ordering("Description", "Descending");
        assertEquals(expected, orderingProductsService.order(database, ordering));
    }

}