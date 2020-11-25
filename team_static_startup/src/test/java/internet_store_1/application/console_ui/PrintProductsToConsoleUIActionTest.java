package internet_store_1.application.console_ui;

import internet_store_1.application.core.domain.Product;
import internet_store_1.application.core.services.GetProductListService;
import internet_store_1.application.database.Database;
import internet_store_1.application.database.InMemoryDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PrintProductsToConsoleUIActionTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Database database = new InMemoryDatabase();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldPrintOutProducts() {
        database.add(new Product("iPhone", "mobile phone", new BigDecimal("950.00")));

        GetProductListService service = new GetProductListService(database);
        PrintProductsToConsoleUIAction victim = new PrintProductsToConsoleUIAction(service);
        victim.execute();
        assertEquals("Product{id = 1, productName = 'iPhone', productDescription = 'mobile phone', price = 950.00}", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldNotPrintOutProducts_whenDatabaseIsEmpty() {
        GetProductListService service = new GetProductListService(database);
        PrintProductsToConsoleUIAction victim = new PrintProductsToConsoleUIAction(service);
        victim.execute();
        assertEquals("Database is empty.", outputStreamCaptor.toString().trim());
    }
}