package internet_store.lesson_3.database;

import internet_store.lesson_3.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface Database {

    Long add(Product product);

    boolean deleteByProductId(Long productId);

    boolean delete(Product product);

    boolean deleteByProductName(String product);

    List<Product> findByProductName(String productName);

    List<Product> findByProductDescription(String productName);

    List<Product> findByNameAndDescription(String name, String description);

    List<Product> getProductList();

    Optional<Product> findById(Long id);

    boolean changeProductName(Long id, String newName);

}
