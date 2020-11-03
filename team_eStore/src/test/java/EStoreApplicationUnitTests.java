import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class EStoreApplicationUnitTests {

    private static ProductRepository productRepository;

    @Before
    public void executedBeforeEach() {
        productRepository = ProductRepository.getProductRepository();
    }

    /**
     *Test for ProductRepository.getProductRepository return same instance.
     */
    @Test
    public void testGetProductRepository() {
        assertSame("Failure if different ProductRepository instances return",
                ProductRepository.getProductRepository(), ProductRepository.getProductRepository());
    }

    /**
     *Test if  initialized productList contains records.
     */
    @Test
    public void testInitializeProductList() {
        assertFalse("Failure in case of empty list",
                productRepository.getProductList().isEmpty());
    }

    /**
     *Test to add product to records.
     */
    @Test
    public void testAddProduct() {
        productRepository.addProduct("product", "newProduct", 11.11);
        assertTrue("Should fail if store doesn't contain new created product",
                productRepository.getProductList().stream().anyMatch(s-> "product".equals(s.getName())));
    }

    /**
     *Test to remove product by 'ID'.
     */
    @Test
    public void testRemoveProductById() {
        productRepository.removeProductById(4);
        assertTrue("Product with ID = 4 (name = product) should be removed",
                productRepository.getProductList().stream().noneMatch(s -> "product".equals(s.getName())));
    }

    /**
     *Test to remove product by 'name'.
     */
    @Test
    public void testRemoveProductByName() {
        productRepository.removeProductByName("mouse");
        assertTrue("Product with name = mouse should be removed from the store",
                productRepository.getProductList().stream().noneMatch(s -> "mouse".equals(s.getName())));
    }

    /**
     *Test to find product by 'ID'.
     */
    @Test
    public void testFindProductById() {
        Product product = productRepository.findProductById(1);
        assertEquals("Should fail if product with particular ID was not found", "laptop",
                product.getName());
    }

    /**
     *Test to find product by 'name'.
     */
    @Test
    public void testFindProductByName() {
        Product product = productRepository.findProductByName("keyboard");
        assertEquals("Should fail if product with particular name was not found",3,
                product.getId());
    }

    /**
     *Test to update product by 'ID'.
     */
    @Test
    public void testUpdateProduct() {
        productRepository.updateProductById(1, "computer", "desktop computer", 222.22);
        assertTrue("New name of laptop, description and price should be changed",
                productRepository.getProductList().stream().anyMatch(s -> "computer".equals(s.getName()))
                        && productRepository.getProductList().stream().anyMatch(s -> "desktop computer".equals(s.getDescription())));
    }
}