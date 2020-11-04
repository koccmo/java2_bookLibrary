package internet_store.application;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    Long save(Product product);

    boolean delete(Long productId);

    boolean delete(Product product);

    boolean deleteByProductName(String product);

    List<Product> findByProductName(String productName);

    List<Product> getProductList();

    Optional<Product> findById(Long id);

//    List<Product> findByProductIDAndChangeName(Long id);

    boolean changeProductName(Long id, String newName);

}
