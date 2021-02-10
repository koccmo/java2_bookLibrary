package lv.estore.app.core.database;

import lv.estore.app.core.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Component
public class InMemoryProductRepositoryImpl implements ProductRepository {

    private List<Product> productList = new ArrayList<>();
    private Long id = 1L;

    /**
     * Method to add product.
     * @param product Product instance.
     * @return true if product was added.
     */
    @Override
    public boolean addProduct(final Product product) {
        product.setId(id);
        id++;
        return productList.add(product);
    }

    /**
     * Method to remove product by 'Id'.
     * @param id Long 'id' of product to remove.
     * @return true if product was removed.
     */
    @Override
    public boolean removeById(final Long id) {
        return productList.removeIf(product -> Objects.equals(product.getId(), id));
    }

    /**
     * Method to remove product by 'Name'.
     * @param name String 'name' of product to remove.
     * @return true if product was removed.
     */
    @Override
    public boolean removeByName(final String name) {
        return productList.removeIf(product -> name.equals(product.getName()));
    }

    /**
     * Method returns all products.
     * @return List<Product> list of stored products.
     */
    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    /**
     * Method to find product by 'Id'.
     * @param id Long 'id' of product to find.
     * @return Product if product was found, else null.
     */
    @Override
    public Product findById(final Long id) {
        return productList
                .stream()
                .filter(product -> Objects.equals(id, product.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Method to find product by 'Name'.
     * @param name String 'name' of product to find.
     * @return Product if product was found, else null.
     */
    @Override
    public List<Product> findByName(final String name) {
        return productList.stream()
                .filter(product -> name.equals(product.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByPrice(BigDecimal price) {
        return productList.stream()
                .filter(product -> price.equals(product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findManyByNameAndPrice(String name, BigDecimal price) {
        return productList.stream()
                .filter(product -> name.equals(product.getName()))
                .filter(product -> price.equals(product.getPrice()))
                .collect(Collectors.toList());
    }

    /**
     * Method to update product by product 'Id'.
     * @param id Long 'id' of product to find.
     * @param newName String 'newName' updated name.
     * @param newDescription String 'newDescription' updated description.
     * @param newPrice String 'newPrice' updated price.
     * @return true if product was updated.
     */
    @Override
    public boolean updateById(final Long id, final String newName, final String newDescription, final BigDecimal newPrice) {
        Product product = null;
        for (Product p : productList) {
            if (Objects.equals(id, p.getId())) {
                product = p;
            }
        }
        if (product != null) {
            product.setName(newName);
            product.setDescription(newDescription);
            product.setPrice(newPrice);
            return true;
        } else {
            return false;
        }
    }
}
