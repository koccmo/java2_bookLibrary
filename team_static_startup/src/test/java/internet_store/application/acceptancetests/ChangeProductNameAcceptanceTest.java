package internet_store.application.acceptancetests;

import internet_store.application.dependency_injection.ApplicationContext;
import internet_store.application.core.database.Database;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.ChangeProductNameRequest;
import internet_store.application.core.responses.ChangeProductNameResponse;
import internet_store.application.core.services.ChangeProductNameService;
import internet_store.application.dependency_injection.DIApplicationContextBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class ChangeProductNameAcceptanceTest {

/*    private ApplicationContext appContext = new ApplicationContext();
    private Database database;

    @Before
    public void setUp() {
        database = getDatabase();
        database.add(new Product("iPhone", "phone", new BigDecimal("900")));
        database.add(new Product("iMac", "pc", new BigDecimal("4000")));
    }

    @Test
    public void shouldChangeProductName() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "iPhone12");
        ChangeProductNameResponse response = getChangeProductNameService().execute(request);

        assertTrue(response.isNameChanged());
        assertEquals("iPhone12", database.getProductList().get(0).getName());
        assertNull(response.getErrors());
    }

    @Test
    public void shouldNotChangeNameWhenProductNotFound() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(3L, "iPhone12");
        ChangeProductNameResponse response = getChangeProductNameService().execute(request);

        assertFalse(response.isNameChanged());
        assertNull(response.getErrors());
    }

    @Test
    public void shouldReturnErrorWhenNewNameIsEmpty() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "");
        ChangeProductNameResponse response = getChangeProductNameService().execute(request);

        assertFalse(response.isNameChanged());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product new name", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

    private Database getDatabase() {
        return appContext.getBean(Database.class);
    }

    private ChangeProductNameService getChangeProductNameService() {
        return appContext.getBean(ChangeProductNameService.class);
    }*/
}