package internet_store.application.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import internet_store.application.domain.Product;
import internet_store.application.database.Database;
import internet_store.application.database.InMemoryDatabase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PrintProductsToConsoleUIActionTest {

    private final PrintStream standartOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Database database = new InMemoryDatabase();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standartOut);
    }

    @Test
    public void shouldPrintOutProducts() {
        database.add(new Product("iPhone", "mobile phone", new BigDecimal("950.00")));
        database.add(new Product("Lenovo ThinkPad", "notebook", new BigDecimal("3000.00")));
        PrintProductsToConsoleUIAction victim = new PrintProductsToConsoleUIAction(database);
        victim.execute();
        assertEquals("Product{id = 1, productName = 'iPhone', productDescription = 'mobile phone', price = 950.00}\n" +
                "Product{id = 2, productName = 'Lenovo ThinkPad', productDescription = 'notebook', price = 3000.00}", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldNotPrintOutProducts_whenDatabaseIsEmpty() {
        PrintProductsToConsoleUIAction victim = new PrintProductsToConsoleUIAction(database);
        victim.execute();
        assertEquals("", outputStreamCaptor.toString().trim());
    }
}