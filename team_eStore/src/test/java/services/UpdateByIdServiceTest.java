package services;

import org.junit.Before;
import org.junit.Test;
import repository.ProductDatabase;
import core.services.UpdateByIdService;

public class UpdateByIdServiceTest {
    private ProductDatabase productDatabase;
    private UpdateByIdService updateInfoService;

    @Before
    public void executedBefore() {
        productDatabase = ProductDatabase.getDataBase();
        //updateInfoService = new UpdateByIdService(productDatabase);
    }

    /**
     * Test for UpdateInfoService.execute().
     * Product info should be updated.
     */

    @Test
    public void execute_Success_Test(){

    }

    /**
     * Test for UpdateInfoService.execute().
     * If product is not found, must fail.
     */
    @Test
    public void execute_Fail_Test(){

    }
}
