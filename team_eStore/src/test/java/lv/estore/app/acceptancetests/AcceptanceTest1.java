package lv.estore.app.acceptancetests;

public class AcceptanceTest1 {
/*
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void testAddProduct_ReturnCorrectProductList() {
        AddProductRequest addProductRequest1 = new AddProductRequest("name", "description", "1.0");
        getAddService().execute(addProductRequest1);

        AddProductRequest addProductRequest2 = new AddProductRequest("name", "description2", "2.0");
        getAddService().execute(addProductRequest2);

        GetAllProductResponse response = getAllService().execute(new GetAllProductsRequest());
        assertEquals(2, response.getProducts().size());
    }

    private AddProductService getAddService() {
        return appContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllService() {
        return appContext.getBean(GetAllProductsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }*/
}
