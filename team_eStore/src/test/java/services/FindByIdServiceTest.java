package services;

import org.junit.Before;
import org.junit.Test;
import repository.ProductDatabase;
import core.services.FindByIdService;

public class FindByIdServiceTest {
    private ProductDatabase productDatabase;
    private FindByIdService findByIdService;

    @Before
    public void executedBefore() {
        productDatabase = ProductDatabase.getDataBase();
        //findByIdService = new FindByIdService(productDatabase, new Validator(idValidationRule));
    }

    /**
     * Test for FindByIdService.execute(). Must return product from the product list,
     * found by 'id'.
     */

    @Test
    public void execute_Success_Test(){

    }

    /**
     * Test for FindByIdService.execute(). Must return null if product is not found.
     */
    @Test
    public void execute_Fail_Test(){

    }
}
