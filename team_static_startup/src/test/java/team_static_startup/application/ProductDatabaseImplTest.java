package team_static_startup.application;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ProductDatabaseImplTest {
    ProductDatabaseImpl productDatabase;

    @Before
    public void setUp() {
        productDatabase = new ProductDatabaseImpl();
    }

    @Test
    public void shouldSaveNewProduct() {
        productDatabase.save(new Product("phone", "good phone", new BigDecimal("327.01")));
        productDatabase.save(new Product("tv", "good tv", new BigDecimal("499.99")));
        assertEquals(2, productDatabase.getProductList().size());
    }

    @Test
	@Ignore
    public void shouldDeleteProductById() {
        productDatabase.save(new Product("tv", "good tv", new BigDecimal("499.99")));
        productDatabase.delete(0L);
        assertEquals(0, productDatabase.getProductList().size());
    }

    @Test
    public void shouldDeleteProductByProductObject() {
        Product tv = new Product("tv", "good tv", new BigDecimal("499.99"));
        productDatabase.save(tv);
        productDatabase.delete(tv);
        assertEquals(0, productDatabase.getProductList().size());
    }

    @Test
    public void shouldDeleteProductByProductName() {
        productDatabase.save(new Product("tv", "good tv", new BigDecimal("499.99")));
        productDatabase.deleteByProductName("tv");
        assertEquals(0, productDatabase.getProductList().size());
    }

    @Test
    public void shouldFindProductByProductName() {
        productDatabase.save(new Product("tv", "good tv", new BigDecimal("499.99")));
        productDatabase.save(new Product("tv", "good tv, good", new BigDecimal("399.99")));
        List<Product> testArray = productDatabase.findByProductName("tv");
        assertEquals(2, testArray.size());
    }
}