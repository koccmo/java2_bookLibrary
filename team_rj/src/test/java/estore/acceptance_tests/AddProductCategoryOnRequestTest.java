package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.AddProductCategoryRequest;
import estore.core.requests.GetAllProductCategoriesRequest;
import estore.core.responses.AddProductCategoryResponse;
import estore.core.service.AddProductCategoryService;
import estore.core.service.GetAllProductCategoriesService;
import estore.database.ProductCategoryRepository;
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
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    @Test
    public void shouldAddValidProductCategory() {
        int dbInitialSize = getAllProductCategoriesService()
                .execute(new GetAllProductCategoriesRequest())
                .getCategories()
                .size();
        AddProductCategoryRequest addProductCategoryRequest1 = new AddProductCategoryRequest("CategoryA");
        AddProductCategoryRequest addProductCategoryRequest2 = new AddProductCategoryRequest("CategoryB");

        addNewProductCategoryService().execute(addProductCategoryRequest1);
        AddProductCategoryResponse response = addNewProductCategoryService().execute(addProductCategoryRequest2);

        assertTrue(response.isSuccessfullyAdded());
        assertEquals(getCategoryDb().getDatabase().size(), dbInitialSize + 2);
        assertEquals(getCategoryDb().getDatabase().get(1).getCategory(), "CategoryB");
    }

    @Test
    public void shouldFailAddingInvalidCategory() {
        int dbInitialSize = getAllProductCategoriesService()
                .execute(new GetAllProductCategoriesRequest())
                .getCategories()
                .size();

        AddProductCategoryRequest addProductCategoryRequest1 = new AddProductCategoryRequest("Invalid category 01");
        AddProductCategoryResponse addResponse = addNewProductCategoryService().execute(addProductCategoryRequest1);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must contain only english letters!");

        AddProductCategoryRequest addProductCategoryRequest2 = new AddProductCategoryRequest("");
        addResponse = addNewProductCategoryService().execute(addProductCategoryRequest2);
        assertTrue(addResponse.hasErrors());
        assertEquals(addResponse.getErrors().size(), 1);
        assertEquals(addResponse.getErrors().get(0).getMessage(), "Must not be empty!");

        assertEquals(getCategoryDb().getDatabase().size(), dbInitialSize);
    }

    private AddProductCategoryService addNewProductCategoryService() {
        return applicationContext.getBean(AddProductCategoryService.class);
    }

    private ProductCategoryRepository getCategoryDb() {
        return applicationContext.getBean(ProductCategoryRepository.class);
    }

    private GetAllProductCategoriesService getAllProductCategoriesService() {
        return applicationContext.getBean(GetAllProductCategoriesService.class);
    }
}
