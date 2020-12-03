package repository;

import domain.Product;

import java.util.List;

public interface iDatabase {

    boolean addProduct(final Product product);
    boolean updateById(final Long id, final String name, final String description, final String price);
    boolean removeById(final Long id);
    boolean removeByName(final String name);
    Product findById(final Long id);
    Product findByName(final String name);
    List<Product> getAllProducts();
    List<Product> findByNameAndPrice(final String name, final String price);
}
