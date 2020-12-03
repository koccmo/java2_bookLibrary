package services;

import core.services.AddService;
import org.junit.Before;
import org.junit.Test;
import repository.ProductDatabase;

public class AddServiceTest {

    private ProductDatabase productDataBase;
    private AddService addService;

    @Before
    public void executedBefore() {
        productDataBase = ProductDatabase.getDataBase();
        //addService = new AddService(productDataBase, new Validator(Arrays.asList(emptyFieldValidationRule, priceValidationRule)));
    }

    /**
     * Test for repository.ProductRepository.getProductRepository return same instance.
     */

    @Test
    public void executeTest(){

    }
}
