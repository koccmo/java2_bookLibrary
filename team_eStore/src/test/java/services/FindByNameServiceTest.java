package services;

import org.junit.Before;
import org.junit.Test;
import repository.ProductDatabase;
import core.services.FindByNameService;

public class FindByNameServiceTest {
    private ProductDatabase productDatabase;
    private FindByNameService findByNameService;

    @Before
    public void executedBefore() {
        productDatabase = ProductDatabase.getDataBase();
        //findByNameService = new FindByNameService(productDatabase);
    }

    /**
     * Test for FindByNameService.execute(). Must return product from the product list,
     * found by 'name'.
     */

    @Test
    public void execute_Success_Test(){

    }

    /**
     * Test for FindByNameService.execute(). Must return null if product is not found.
     */
    @Test
    public void execute_Fail_Test(){

    }
}
