import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EStoreApplicationUnitTests {

    private static ProductRepository productRepository;

    @Before
    public void executedBeforeEach() {
        productRepository = ProductRepository.getProductRepository();
    }

    /**
     * Test for ProductRepository.getProductRepository return same instance.
     */
    @Test
    public void testGetProductRepository() {
        assertSame("Failure in case of different ProductRepository instances",
                ProductRepository.getProductRepository(), ProductRepository.getProductRepository());
    }

    /**
     * Test for private constructor.
     */
    @Test
    public void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<ProductRepository> constructor = ProductRepository.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    /**
     * Test if initialized productList contains records.
     */
    @Test
    public void testInitializeProductList() {
        assertFalse("Failure in case of empty list",
                productRepository.getProductList().isEmpty());
    }

    /**
     * Test to add new record should success.
     * If product with same info does not exist in the store, product should be added.
     */
    @Test
    public void testAddProductSuccess() {
        final int productListSize = productRepository.getProductList().size();
        productRepository.addProduct("product", "newProduct", 11.11);
        assertTrue("Product list size should increase", productListSize < productRepository.getProductList().size());
        assertTrue("Should fail if store doesn't contain new created product",
                productRepository.getProductList().stream()
                        .anyMatch(product-> "product".equals(product.getName())
                        && "newProduct".equals(product.getDescription())
                        && (0 == Double.compare(11.11, product.getPrice()))));
    }

    /**
     * Test to add new record should fail.
     * If product with same info exists in the store, product should not be added.
     */
    @Test
    public void testAddProductFail() {
        final int productListSize = productRepository.getProductList().size();
        productRepository.addProduct("laptop", "standard laptop with 15\" display", 800.0);
        assertEquals("Size of productList should not be changed", productListSize,
                productRepository.getProductList().size());
    }

    /**
     * Test to remove product by 'ID' success.
     * If product with particular ID exists, product should be removed.
     */
    @Test
    public void testRemoveProductByIdSuccess() {
        final int productListSize = productRepository.getProductList().size();
        final Product productFound = productRepository.findProductById(4);
        productRepository.removeProductById(4);

        assertEquals("Size of productList should be changed to " + (productListSize - 1) + "", productListSize - 1,
                productRepository.getProductList().size());
        assertFalse("Product with ID = 4 should be removed",
                productRepository.getProductList().contains(productFound));
    }

    /**
     * Test to remove product by 'ID' fail.
     * If product with particular ID does not exist, product should not be found and removed.
     */
    @Test
    public void testRemoveProductByIdFail() {
        final int productListSize = productRepository.getProductList().size();
        productRepository.removeProductById(999);
        assertEquals("Size of productList should remain the same", productListSize,
                productRepository.getProductList().size());
    }

    /**
     * Test to remove product by 'name' success.
     * If product with particular 'name' exists, product should be removed.
     */
    @Test
    public void testRemoveProductByNameSuccess() {
        final int currentSize = productRepository.getProductList().size();
        final Product productFound = productRepository.findProductByName("mouse");
        productRepository.removeProductByName("mouse");
        assertEquals("Size of productList should be changed to " + (currentSize - 1) + "", currentSize - 1,
                productRepository.getProductList().size());
        assertFalse("Product with should be removed from the store",
                productRepository.getProductList().contains(productFound));
    }

    /**
     * Test to remove product by 'name' fail.
     * If product with particular 'name' does not exist, product should not be found and removed.
     */
    @Test
    public void testRemoveProductByNameFail() {
        final int currentSize = productRepository.getProductList().size();
        productRepository.removeProductByName("qwerty");
        assertEquals("Size of productList should not be changed " + currentSize , currentSize,
                productRepository.getProductList().size());
    }

    /**
     * Test to find product by 'ID' success.
     * If product with particular 'ID' exists, product should be found.
     */
    @Test
    public void testFindProductByIdSuccess() {
        final Product product = productRepository.findProductById(1);
        assertNotNull("Should fail if product with particular \"ID\" was not found",
                product);
    }

    /**
     * Test to find product by 'ID' fail.
     * If product with particular 'ID' does not exist, product should not be found.
     */
    @Test
    public void testFindProductByIdFail() {
        final Product product = productRepository.findProductById(999);
        assertNull("Product with \"id=999\" should not be found",
                product);
    }

    /**
     * Test to find product by 'name' success.
     * If product with particular 'name' exists, product should be found.
     */
    @Test
    public void testFindProductByNameSuccess() {
        final Product product = productRepository.findProductByName("keyboard");
        assertNotNull("Should fail if product with \"name = keyboard\" was not found",
                product);
    }

    /**
     * Test to find product by 'name' fail.
     * If product with particular 'name' does not exist, product should not be found.
     */
    @Test
    public void testFindProductByNameFail() {
        final Product product = productRepository.findProductByName("qwerty");
        assertNull("Product with \"name=qwerty\" should not be found",
                product);
    }

    /**
     * Test to update product by 'ID' should success.
     * If product with particular 'ID' exists, product info should be updated.
     */
    @Test
    public void testUpdateProductSuccess() {
        final Product product = productRepository.findProductById(1);
        final String foundName = product.getName();
        final String foundDescription = product.getDescription();
        final double price = product.getPrice();

        productRepository.updateProductById(1, "computer", "desktop computer", 222.22);

        assertTrue("After product is updated, product previous info should not be found",
                productRepository.getProductList().stream()
                        .noneMatch(s -> foundName.equals(s.getName())
                        && foundDescription.equals(s.getDescription())
                        && (0 == Double.compare(price, product.getPrice()))));
    }

    /**
     * Test to update product by 'ID' should fail.
     * After update, info of new product should not be found.
     */
    @Test
    public void testUpdateProductFail() {
        final String name = "newProduct";
        final String description = "new description";
        final double price = 88888.88;
        productRepository.updateProductById(999, name, description, price);

        assertTrue("After product is updated, product previous info should not be found",
                productRepository.getProductList().stream()
                        .noneMatch(product -> name.equals(product.getName())
                        && description.equals(product.getDescription())
                        && (0 == Double.compare(price, product.getPrice()))));
    }
}