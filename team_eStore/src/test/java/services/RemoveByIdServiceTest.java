package services;

import core.services.RemoveByIdService;
import org.junit.Before;
import org.junit.Test;
import repository.ProductDatabase;

public class RemoveByIdServiceTest {
    private ProductDatabase productDatabase;
    private RemoveByIdService removeByIdService;

    @Before
    public void executedBefore() {
        productDatabase = ProductDatabase.getDataBase();
        //removeByIdService = new RemoveByIdService(productDatabase, new Validator(Arrays.asList(emptyFieldValidationRule, idValidationRule)));
    }

    /**
     * Test for RemoveByIdService.execute().
     * Product should be deleted from the product list.
     */

    @Test
    public void execute_Success_Test(){

    }

    /**
     * Test for FindByIdService.execute().
     * Should fail, if product which is should be deleted is not found.
     */
    @Test
    public void execute_Fail_Test(){

    }
}
