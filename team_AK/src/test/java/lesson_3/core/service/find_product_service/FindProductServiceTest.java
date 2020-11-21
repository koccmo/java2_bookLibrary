package lesson_3.core.service.find_product_service;

import lesson_3.core.domain.Product;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FindProductServiceTest {
    private static final List<Product> products = new ArrayList<>();
    FindProductService findService = new FindProductService();

    @BeforeClass
    public static void setup() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5);
        product.setPrice(new BigDecimal("100"));
        products.add(product);

        Product product_1 = new Product();
        product_1.setId(2L);
        product_1.setTitle("Title_2");
        product_1.setDescription("Description_2");
        product_1.setQuantity(10);
        product_1.setPrice(new BigDecimal("200"));
        products.add(product_1);

        Product product_2 = new Product();
        product_2.setId(3L);
        product_2.setTitle("Title_3");
        product_2.setDescription("Description_3");
        product_2.setQuantity(15);
        product_2.setPrice(new BigDecimal("300"));
        products.add(product_2);
    }

    @Test
    public void checkId_1() {
        boolean result = findService.isIdExist(products, 1L);
        assertTrue(result);
    }

    @Test
    public void checkId_2() {
        boolean result = findService.isIdExist(products, 3L);
        assertTrue(result);
    }

    @Test
    public void mustReturnFalse_IdNoExist() {
        boolean result = findService.isIdExist(products, 10L);
        assertFalse(result);
    }

    @Test
    public void findProductById_1() {
        Product product = findService.findById(products, 1);
        long id = product.getId();
        assertEquals(1L, id);
        assertEquals("Title", product.getTitle());
    }

    @Test
    public void findProductById_MustReturnNull() {
        Product product = findService.findById(products, 5);
        assertNull(product);
    }

    @Test
    public void findProductIndex_1() {
        int result = findService.findProductIndex(products,1);
        assertEquals(0,result);
    }

    @Test
    public void findProductIndex_MustReturnError() {
        int result = findService.findProductIndex(products,15);
        assertEquals(-1,result);
    }

    @Test
    public void checkProductTitle() {
        boolean result = findService.isProductTitleExist(products,"Title_2");
        assertTrue(result);
    }

    @Test
    public void checkProductTitle_MustReturnError() {
        boolean result = findService.isProductTitleExist(products,"Title_8");
        assertFalse(result);
    }
}