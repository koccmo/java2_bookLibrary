package services;

import org.junit.Before;
import org.junit.Test;
import repository.ProductDatabase;
import core.services.RemoveByNameService;

public class RemoveByNameServiceTest {
    private ProductDatabase productDatabase;
    private RemoveByNameService removeByNameService;

    @Before
    public void executedBefore() {
        productDatabase = ProductDatabase.getDataBase();
        //removeByNameService = new RemoveByNameService(productDatabase);
    }

    /**
     * Test for RemoveByNameService.execute().
     * Product should be deleted from the product list.
     */

    @Test
    public void execute_Success_Test(){

    }

    /**
     * Test for RemoveByNameService.execute().
     * Product should not be deleted, if it was not found.
     */
    @Test
    public void execute_Fail_Test(){

    }
}
