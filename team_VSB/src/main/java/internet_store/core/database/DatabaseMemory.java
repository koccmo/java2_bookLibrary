package internet_store.core.database;

import internet_store.core.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseMemory implements  Database {

    private Long id = 1L;
    private final List<Product> productsList = new ArrayList<>();

    @Override
    public Long add(Product product) {
        product.setId(id);
        productsList.add(product);
        return ++id;
    }

    @Override
    public boolean deleteByProductId(Long productId) {
        for (Product product : productsList) {
            if (product.getId().equals(productId)) {
                productsList.remove(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        for (Product products : productsList) {
            if ((products.getName().equals(product.getName())) && (products.getDescription().equals(product.getDescription()))) {
                productsList.remove(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByProductName(String productName) {
        return productsList.removeIf(products -> (products.getName().equals(productName)));
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return productsList.stream()
                .filter(productInDatabase -> productInDatabase.getName().equalsIgnoreCase(productName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByProductDescription(String productDescription) {
        return productsList.stream()
                .filter(product -> product.getDescription().equalsIgnoreCase(productDescription))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByProductNameAndDescription(String prodcutName, String productDescription) {
        return productsList.stream()
                .filter(product -> product.getName().equals(prodcutName))
                .filter(product -> product.getDescription().equals(productDescription))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductList() {
        return new ArrayList<>(productsList);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productsList.stream()
                .filter(productInDatabase -> productInDatabase.getId().equals(productId))
                .findFirst();
    }

    @Override
    public boolean changeProductName(Long productId, String newName) {
        for (Product products : productsList) {
            if (products.getId().equals(productId)) {
                products.setName(newName);
                return true;
            }
        }
        return false;
    }
}