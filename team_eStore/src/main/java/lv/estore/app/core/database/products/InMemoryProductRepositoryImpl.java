package lv.estore.app.core.database.products;

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

    @Override
    public Long addProduct(final Product product) {
        product.setId(id);
        productList.add(product);
        return id++;
    }

    @Override
    public boolean removeProductById(final Long id) {
        return productList.removeIf(product -> Objects.equals(product.getId(), id));
    }

    @Override
    public boolean removeProductByName(final String name) {
        return productList.removeIf(product -> name.equals(product.getName()));
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product findProductById(final Long id) {
        return productList
                .stream()
                .filter(product -> Objects.equals(id, product.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findProductsByName(final String name) {
        return productList.stream()
                .filter(product -> name.equals(product.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsByPrice(BigDecimal price) {
        return productList.stream()
                .filter(product -> price.equals(product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsByNameAndPrice(String name, BigDecimal price) {
        return productList.stream()
                .filter(product -> name.equals(product.getName()))
                .filter(product -> price.equals(product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateProductById(final Long id, final String newName, final String newDescription, final BigDecimal newPrice) {
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
