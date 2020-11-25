package internet_store.database.product;

import internet_store.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    List <Product> getProducts();

    void add(Product product);

    void deleteById(long id);

    Optional<Product> findById(Long id);

    void changeTitle(long id, String newTitle);

    void changeDescription(long id, String newDescription);

    void changePrice(long id, Integer newPrice);

    List<Product> findAllByTitle (String title);

    List<Product> findAllByDescription (String description);

    List<Product> findAllByTitleAndDescription (String title, String description);
}
