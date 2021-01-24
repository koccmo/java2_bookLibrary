package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.AddNewProductCategoryRequest;
import estore.core.responses.AddNewProductCategoryResponse;
import estore.core.service.AddNewProductCategoryService;
import estore.database.ProductCategoryDB;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductCategoryOnRequestTest {

    private static ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(ProductConfiguration.class);
    }

    @Test
    public void shouldAddValidProductCategory() {
//        int dbInitialSize = getCategoryDb().getDatabase().size();
//        AddNewProductCategoryRequest addProductCategoryRequest1 = new AddNewProductCategoryRequest("CategoryA");
//        AddNewProductCategoryRequest addProductCategoryRequest2 = new AddNewProductCategoryRequest("CategoryB");
//
//        addNewProductCategoryService().execute(addProductCategoryRequest1);
//        AddNewProductCategoryResponse response = addNewProductCategoryService().execute(addProductCategoryRequest2);
//
//        assertTrue(response.isSuccessfullyAdded());
//        assertEquals(getCategoryDb().getDatabase().size(), dbInitialSize + 2);
//        assertEquals(getCategoryDb().getDatabase().get(dbInitialSize + 1).getCategory(), "CategoryB");
    }

    @Test
    public void shouldFailAddingInvalidCategory() {
        int dbInitialSize = getCategoryDb().getDatabase().size();

        AddNewProductCategoryRequest addProductCategoryRequest1 = new AddNewProductCategoryRequest("Invalid category 01");
        AddNewProductCategoryResponse addResponse = addNewProductCategoryService().execute(addProductCategoryRequest1);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must contain only english letters!");

        AddNewProductCategoryRequest addProductCategoryRequest2 = new AddNewProductCategoryRequest("");
        addResponse = addNewProductCategoryService().execute(addProductCategoryRequest2);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().size(), 1);
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must not be empty!");

        assertEquals(getCategoryDb().getDatabase().size(), dbInitialSize);
    }

    private AddNewProductCategoryService addNewProductCategoryService() {
        return applicationContext.getBean(AddNewProductCategoryService.class);
    }

    private ProductCategoryDB getCategoryDb() {
        return applicationContext.getBean(ProductCategoryDB.class);
    }
}
