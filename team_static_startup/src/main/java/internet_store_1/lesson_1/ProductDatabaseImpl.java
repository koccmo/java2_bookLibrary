package internet_store_1.lesson_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDatabaseImpl implements ProductDatabase {
    private Long id = 1L;
    private final List<Product> productList = new ArrayList<>();

    @Override
    public Long save(Product product) {
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
        List<Product> productsByName = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().equals(productName.toLowerCase())) {
                productsByName.add(product);
            }
        }
        return productsByName;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        for (Product products : productList) {
            if (products.getId().equals(productId)) {
                return Optional.of(products);
            }
        }
        return Optional.empty();
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
