package internet_store.application.console_ui;

import static org.junit.Assert.*;

import internet_store.application.core.services.FindProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import internet_store.application.core.domain.Product;
import internet_store.application.database.Database;
import internet_store.application.database.InMemoryDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FindByProductNameUIActionTest {

    Database database = new InMemoryDatabase();
    FindProductService findProductService = new FindProductService(database);
    private final ByteArrayInputStream inputNameToFind = new ByteArrayInputStream("Pineapple".getBytes());
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    Product product1 = new Product("PineApple", "Very good", new BigDecimal("5"));
    Product product2 = new Product("Orange", "Sweet", new BigDecimal("2"));
    Product product3 = new Product("PINEAPPLE", "Bad", new BigDecimal("1"));

    @Test
    public void ShouldFindTwoProductsWithSameName () {

        database.add(product1);
        database.add(product2);
        database.add(product3);

        List<Product> testList = new ArrayList<>(database.findByProductName("Pineapple"));
        assertEquals(testList.get(0), product1);
        assertEquals(testList.get(1), product3);
        assertEquals(2, testList.size());
    }

    @Test
    public void ShouldFindNoProducts () {

        database.add(product1);
        database.add(product2);

        List<Product> testList = new ArrayList<>(database.findByProductName("Coconut"));
        assertTrue(testList.isEmpty());
    }

    @Before
    public void setUp() {
        System.setIn(inputNameToFind);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(standardOut);
    }

    @Test
    public void ShouldFindNoProductsViaUIAction () {
        database.add(product2);

        FindByProductNameUIAction testFind = new FindByProductNameUIAction(findProductService);
        testFind.execute();

        assertEquals(
                "Enter product name to search for: \n"
                        + "No product with name = Pineapple in the database"
                        ,outputStreamCaptor.toString().trim());
    }


}


