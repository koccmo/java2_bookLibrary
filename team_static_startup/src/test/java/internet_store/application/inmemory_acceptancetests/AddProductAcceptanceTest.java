package internet_store.application.inmemory_acceptancetests;

import org.springframework.context.annotation.Profile;

import static org.junit.Assert.assertEquals;

@Profile("inmemory")
public class AddProductAcceptanceTest {
/*
    private ApplicationContext applicationContext;

    @Before
    public void setUp(){
        applicationContext =
                new AnnotationConfigApplicationContext(ProductListConfiguration.class);
    }

    @Test
    public void shouldReturnNoErrorsWhenAddingBooks() {
        AddProductRequest addProductRequest1 = new AddProductRequest("tv1",
                "good tv1", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest1);

        AddProductRequest addProductRequest2 = new AddProductRequest("tv2",
                "good tv2", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest2);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(2, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterName() {
        AddProductRequest addProductRequest = new AddProductRequest(" ",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(0, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterDescription() {
        AddProductRequest addProductRequest = new AddProductRequest("tv",
                null, new BigDecimal("399.99"));
        getAddProductService().execute(addProductRequest);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(0, response.getProductList().size());
    }

    @Test
    public void shouldNotAddBookWithWrongParameterPrice() {
        AddProductRequest addProductRequest = new AddProductRequest("tv",
                "good tv", null);
        getAddProductService().execute(addProductRequest);

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService().execute(request);
        assertEquals(0, response.getProductList().size());
    }

    private AddProductService getAddProductService() {
        return applicationContext.getBean(AddProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return applicationContext.getBean(GetAllProductsService.class);
    }
    */
}
