package internet_store.application.database;

import internet_store.application.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface Database {

    Long add(Product product);

    boolean deleteByProductId(Long productId);

    boolean delete(Product product);

    boolean deleteByProductName(String product);

    List<Product> findByProductName(String productName);

    List<Product> getProductList();

    List<Product> findById(Long id);

    boolean changeProductName(Long id, String newName);

}
