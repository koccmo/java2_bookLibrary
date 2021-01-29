package estore.database;

//import estore.database.inmemoryrepo.ProductRepositoryImpl;

public class ProductDBImplTest {

//    ProductRepositoryImpl productDB = new ProductRepositoryImpl();

//    @Test
//    public void shouldNotReturnErrorIfIdIncreasesByOneWhenAddindNewProductIdTest() {
//        Long currentId = productDB.getDatabase().get(productDB.getDatabase().size() - 1).getId();
//        productDB.addNewProduct(new Product("Pie", "American pie", "Bakery"));
//        assertTrue(productDB.getDatabase().get(productDB.getDatabase().size() - 1).getId() == (currentId + 1));
//    }
//
//    @Test
//    public void shouldNotReturnErrorIfSearchProductByNameFindsProductTest() {
//        productDB.addNewProduct(new Product("TestProduct", "Test description", "Bakery"));
//        assertEquals(productDB.searchProductByName("TestProduct").get(0).getName(), "TestProduct");
//        assertEquals(productDB.searchProductByName("TestProduct").get(0).getDescription(), "Test description");
//        assertEquals(productDB.searchProductByName("TestProduct").get(0).getCategory(), "Bakery");
//    }
//
//    @Test
//    public void shouldNotReturnErrorIfSearchProductByCategoryFindsProductTest() {
//        int currentBakeryProducts = productDB.searchProductByCategory("Bakery").size();
//        productDB.addNewProduct(new Product("TestProduct", "Test description", "Bakery"));
//        assertTrue(productDB.searchProductByCategory("Bakery").size() == (currentBakeryProducts + 1));
//    }
//
//    @Test
//    public void shouldNotReturnErrorIfNewProductSuccessfullyAddedTest() {
//        int currentBakeryProducts = productDB.searchProductByCategory("Bakery").size();
//        shouldNotReturnErrorIfSearchProductByNameFindsProductTest();
//        assertTrue(productDB.searchProductByCategory("Bakery").size() == (currentBakeryProducts + 1));
//        assertTrue(productDB.addNewProduct(new Product("TestProduct2",
//                "Test description2", "Bakery")) == true);
//    }
//
//    @Test
//    public void shouldNotReturnErrorIfProductIsSuccessfullyRemovedByIdTest() {
//        Product testProduct = new Product("TestProduct", "Test description", "Bakery");
//        productDB.addNewProduct(testProduct);
//        Long productId = productDB.getDatabase().get(productDB.getDatabase().size() - 1).getId();
//        assertTrue(productDB.removeProductById(productId) == 1);
//        assertFalse(productDB.getDatabase().contains(testProduct));
//    }
}