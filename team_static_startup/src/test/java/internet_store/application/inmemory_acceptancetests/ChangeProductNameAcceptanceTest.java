package internet_store.application.inmemory_acceptancetests;

import org.springframework.context.annotation.Profile;

@Profile("inmemory")
public class ChangeProductNameAcceptanceTest {
/*
    private ApplicationContext applicationContext;
    private Database database;

    @Before
    public void setUp() {
        applicationContext =
                new AnnotationConfigApplicationContext(ProductListConfiguration.class);
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
        return applicationContext.getBean(Database.class);
    }

    private ChangeProductNameService getChangeProductNameService() {
        return applicationContext.getBean(ChangeProductNameService.class);
    }*/
}