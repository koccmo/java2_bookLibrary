package services;

import org.junit.Before;
import org.junit.Test;
import repository.ProductDatabase;
import core.services.FindAllService;

public class FindAllServiceTest {
    private ProductDatabase productDatabase;
    private FindAllService findAllService;

    @Before
    public void executedBefore() {
        productDatabase = ProductDatabase.getDataBase();
        findAllService = new FindAllService(productDatabase);
    }

    /**
     * Test for FindAllService.execute(). Must return all the products from the product list.
     */

    @Test
    public void execute_Success_Test(){

    }

    /**
     * Test for AddService.execute(). Must return null if product list is empty.
     */
    @Test
    public void execute_Fail_Test(){

    }
}
