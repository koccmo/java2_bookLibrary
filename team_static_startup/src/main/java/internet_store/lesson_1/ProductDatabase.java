package internet_store.lesson_1;

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

    boolean changeProductName(Long id, String newName);

}
