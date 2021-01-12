package lv.estore.app.core.repository;

import lv.estore.app.core.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface iDatabase {

    boolean addProduct(final Product product);
    boolean updateById(final Long id, final String name, final String description, final BigDecimal price);
    boolean removeById(final Long id);
    boolean removeByName(final String name);
    Product findById(final Long id);
    Product findByName(final String name);
    List<Product> findManyByName(final String name);
    List<Product> findManyByPrice(final BigDecimal price);
    List<Product> findManyByNameAndPrice(final String name, final BigDecimal price);
    List<Product> getAllProducts();
}
