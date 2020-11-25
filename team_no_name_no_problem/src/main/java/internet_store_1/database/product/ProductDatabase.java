package internet_store_1.database.product;

import internet_store_1.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    List <Product> getProducts();

    void add(Product product);

    void deleteById(long id);

    Optional<Product> findById(Long id);

    void changeTitle(long id, String newTitle);

    void changeDescription(long id, String newDescription);

    Optional<Product> findAnyByTitle (String title);

    List<Product> findAllByTitle (String title);
}
