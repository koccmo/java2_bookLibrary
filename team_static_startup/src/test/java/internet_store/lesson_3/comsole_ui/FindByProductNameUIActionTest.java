package internet_store.lesson_3.comsole_ui;

import internet_store.lesson_3.console_ui.FindByProductNameUIAction;
import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.services.FindByProductNameService;
import internet_store.lesson_3.core.services.validators.FindProductValidator;
import internet_store.lesson_3.database.Database;
import internet_store.lesson_3.database.InMemoryDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FindByProductNameUIActionTest {

    Database database = new InMemoryDatabase();
    FindProductValidator validator = new FindProductValidator();
    FindByProductNameService findByProductNameService = new FindByProductNameService(database, validator);
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

        FindByProductNameUIAction testFind = new FindByProductNameUIAction(findByProductNameService);
        testFind.execute();

        assertEquals(
                "Enter product name to search for: \n"
                        + "No product with name = Pineapple found in the database"
                        ,outputStreamCaptor.toString().trim());
    }


}


