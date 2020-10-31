package internet_store;

import org.junit.Test;

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

}