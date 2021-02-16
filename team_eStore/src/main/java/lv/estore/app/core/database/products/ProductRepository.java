package lv.estore.app.core.database.products;

import lv.estore.app.core.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {

    Long addProduct(final Product product);
    boolean updateProductById(final Long id, final String name, final String description, final BigDecimal price);
    boolean removeProductById(final Long id);
    boolean removeProductByName(final String name);
    Product findProductById(final Long id);
    List<Product> findProductsByName(final String name);
    List<Product> findProductsByPrice(final BigDecimal price);
    List<Product> findProductsByNameAndPrice(final String name, final BigDecimal price);
    List<Product> getAllProducts();
}
