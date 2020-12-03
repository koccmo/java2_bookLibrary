package repository;

import org.junit.Before;
import org.junit.Test;
import core.services.AddService;

public class ProductDatabaseTest {

    private ProductDatabase productDatabase;
    private AddService addService;

    @Before
    public void executedBefore() {
        productDatabase = ProductDatabase.getDataBase();
    }

    /**
     * Test for AddService.execute(). Must add new product to the ProductDatabase,
     * if the similar product does not exists.
     */

    @Test
    public void execute_Success_Test(){

    }

    /**
     * Test for AddService.execute(). Must fail because, product already has record
     * in ProductDatabase.
     */
    @Test
    public void execute_Fail_Test(){

    }
}
