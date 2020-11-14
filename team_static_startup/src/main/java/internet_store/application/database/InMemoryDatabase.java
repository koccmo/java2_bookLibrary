package internet_store.application.database;

import internet_store.application.core.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDatabase implements Database {
    private Long id = 1L;
    private final List<Product> productList = new ArrayList<>();

    @Override
    public Long add(Product product) {
        product.setId(id);
        productList.add(product);
        return ++id;
    }

    @Override
    public boolean delete(Long productIdLong) {
        for (Product product : productList) {
            if (product.getId().equals(productIdLong)) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        for (Product products : productList) {
            if ((products.getName().equals(product.getName())) && (products.getDescription().equals(product.getDescription()))) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByProductName(String productName) {
        return productList.removeIf(products -> (products.getName().equals(productName)));
    }

    @Override
    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return productList.stream()
                .filter(productInDataBase -> productInDataBase.getName().equalsIgnoreCase(productName))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productList.stream()
                .filter(productInDataBase -> productInDataBase.getId().equals(productId))
                .findFirst();
    }

    @Override
    public boolean changeProductName(Long productId, String newName) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                product.setName(newName);
                return true;
            }
        }
        return false;
    }

}
