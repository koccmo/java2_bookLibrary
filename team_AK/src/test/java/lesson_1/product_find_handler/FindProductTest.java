package lesson_1.product_find_handler;

import internet_store.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class FindProductTest {
    private static final List<Product> products = new ArrayList<>();
    private static final FindProduct findProduct = new FindProduct();

    @Before
    public void startUp() {
        Product product_1 = new Product();
        product_1.setId(1L);
        product_1.setTitle("Product 1");

        Product product_2 = new Product();
        product_2.setId(2L);
        product_2.setTitle("Product 2");

        Product product_3 = new Product();
        product_3.setId(3L);
        product_3.setTitle("Product 3");

        products.add(product_1);
        products.add(product_2);
        products.add(product_3);
    }

    @Test
    public void isIdExist_1() {
        boolean result = findProduct.isIdExist(products, 1);
        assertTrue(result);
    }

    @Test
    public void isIdExist_2() {
        boolean result = findProduct.isIdExist(products, 2);
        assertTrue(result);
    }

    @Test
    public void isIdExist_3() {
        boolean result = findProduct.isIdExist(products, 3);
        assertTrue(result);
    }

    @Test
    public void isIdNotExist() {
        boolean result = findProduct.isIdExist(products, 4);
        assertFalse(result);
    }

    @Test
    public void findById_1() {
        Product product = findProduct.findById(products, 1);
        assertEquals("Product 1", product.getTitle());
    }

    @Test
    public void findById_2() {
        Product product = findProduct.findById(products, 2);
        assertEquals("Product 2", product.getTitle());
    }

    @Test
    public void findById_3() {
        Product product = findProduct.findById(products, 3);
        assertEquals("Product 3", product.getTitle());
    }

    @Test
    public void findById_ProductNoExist() {
        Product product = findProduct.findById(products, 10);
        assertNull(product);
    }

    @Test
    public void findProductIndex_1() {
        int result = findProduct.findProductId(products, 1);
        assertEquals(0, result);

    }

    @Test
    public void findProductIndex_2() {
        int result = findProduct.findProductId(products, 2);
        assertEquals(1, result);
    }

    @Test
    public void findProductIndex_3() {
        int result = findProduct.findProductId(products, 3);
        assertEquals(2, result);
    }

    @Test
    public void findProductIndex_IndexNotExist() {
        int result = findProduct.findProductId(products, 4);
        assertEquals(-1, result);
    }
}