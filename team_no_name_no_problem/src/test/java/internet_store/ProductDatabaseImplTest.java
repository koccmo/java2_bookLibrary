package internet_store;

import internet_store.domain.Product;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDatabaseImplTest {

    @Test
    public void saveProductTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        boolean saveResult1 = productDatabase.save(firstProduct);
        boolean saveResult2 = productDatabase.save(secondProduct);
        assertTrue(saveResult1);
        assertTrue(saveResult2);
    }

    @Test
    public void saveProductTestNotValidInput() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Laptop","Dell",400);
        boolean saveResult1 = productDatabase.save(firstProduct);
        boolean saveResult2 = productDatabase.save(secondProduct);
        assertTrue(saveResult1);
        assertFalse(saveResult2);
    }

    @Test
    public void generateProductIdTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        Long firstId = firstProduct.getId();
        Long secondId = secondProduct.getId();
        assertTrue(firstId == 1L);
        assertTrue(secondId == 2L);
    }

    @Test
    public void deleteByIdTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        boolean deleteResult = productDatabase.deleteById(1L);
        assertTrue(deleteResult);
    }

    @Test
    public void deleteByIdTest2() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        boolean deleteResult = productDatabase.deleteById(2L);
        assertTrue(deleteResult);
    }

    @Test
    public void deleteByIdTestNoIdInDatabase() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        assertFalse(productDatabase.deleteById(7L));
    }

    @Test
    public void getProductListTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop","Dell",400);
        Product secondProduct = new Product("Refrigerator","Electrolux",300);
        Product thirdProduct = new Product("TV","Radiotehnika",3);
        productDatabase.save(firstProduct);
        productDatabase.save(secondProduct);
        List<Product> listOfAllProducts = productDatabase.getProductList();
        assertTrue(listOfAllProducts.contains(firstProduct));
        assertTrue(listOfAllProducts.contains(secondProduct));
        assertFalse(listOfAllProducts.contains(thirdProduct));
        assertEquals(listOfAllProducts.size(),2);
    }
}
