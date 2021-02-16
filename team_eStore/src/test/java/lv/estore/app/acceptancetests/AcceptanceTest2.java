package lv.estore.app.acceptancetests;

public class AcceptanceTest2 {
/*
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void searchProductsByName() {
        AddProductRequest request1 = new AddProductRequest("name", "description", "1.0");
        getAddService().execute(request1);

        AddProductRequest request2 = new AddProductRequest("name", "description2", "2.0");
        getAddService().execute(request2);

        SearchProductByNamePriceRequest request3 = new SearchProductByNamePriceRequest("name", null);
        SearchProductResponse response = getSearchService().execute(request3);

        assertEquals(2, response.getProducts().size());
        assertEquals("name", response.getProducts().get(0).getName());
        assertEquals("description", response.getProducts().get(0).getDescription());
        assertEquals("name", response.getProducts().get(1).getName());
        assertEquals("description2", response.getProducts().get(1).getDescription());
    }

    @Test
    public void searchProductsByName_OrderingByPrice_Descending() {
        AddProductRequest request1 = new AddProductRequest("name", "description1", "1.0");
        getAddService().execute(request1);

        AddProductRequest request2 = new AddProductRequest("name", "description2", "2.0");
        getAddService().execute(request2);

        Ordering ordering = new Ordering("price", "DESCENDING");
        SearchProductByNamePriceRequest request3 = new SearchProductByNamePriceRequest("name", null, ordering);
        SearchProductResponse response = getSearchService().execute(request3);

        assertEquals(2, response.getProducts().size());
        assertEquals("name", response.getProducts().get(0).getName());
        assertEquals("description2", response.getProducts().get(0).getDescription());
        assertEquals("2.00", response.getProducts().get(0).getPrice().toPlainString());
        assertEquals("name", response.getProducts().get(1).getName());
        assertEquals("description1", response.getProducts().get(1).getDescription());
        assertEquals("1.00", response.getProducts().get(1).getPrice().toPlainString());
    }

    @Test
    public void searchProductsByPrice_OrderingByName_Ascending() {
        AddProductRequest request1 = new AddProductRequest("name2", "description2", "1.0");
        getAddService().execute(request1);

        AddProductRequest request2 = new AddProductRequest("name1", "description1", "1.0");
        getAddService().execute(request2);

        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchProductByNamePriceRequest request3 = new SearchProductByNamePriceRequest(null, "1.0", ordering);
        SearchProductResponse response = getSearchService().execute(request3);

        assertEquals(2, response.getProducts().size());
        assertEquals("name1", response.getProducts().get(0).getName());
        assertEquals("description1", response.getProducts().get(0).getDescription());
        assertEquals("1.00", response.getProducts().get(0).getPrice().toPlainString());
        assertEquals("name2", response.getProducts().get(1).getName());
        assertEquals("description2", response.getProducts().get(1).getDescription());
        assertEquals("1.00", response.getProducts().get(1).getPrice().toPlainString());
    }

    @Test
    public void searchProductsByName_OrderingByPrice_Paging() {
        AddProductRequest request1 = new AddProductRequest("name", "description2", "2.0");
        getAddService().execute(request1);

        AddProductRequest request2 = new AddProductRequest("name", "description1", "1.0");
        getAddService().execute(request2);

        Ordering ordering = new Ordering("price", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchProductByNamePriceRequest request3 = new SearchProductByNamePriceRequest("name", null, ordering, paging);
        SearchProductResponse response = getSearchService().execute(request3);

        assertEquals(1, response.getProducts().size());
        assertEquals("name", response.getProducts().get(0).getName());
        assertEquals("description1", response.getProducts().get(0).getDescription());
        assertEquals("1.00", response.getProducts().get(0).getPrice().toPlainString());
    }

    private AddProductService getAddService() {
        return appContext.getBean(AddProductService.class);
    }

    private SearchProductsService getSearchService() {
        return appContext.getBean(SearchProductsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }*/
}