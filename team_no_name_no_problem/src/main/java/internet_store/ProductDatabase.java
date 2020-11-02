package internet_store;

import internet_store.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    boolean save(Product product);

    boolean deleteById(long id);

    boolean printProducts();

    boolean changeTitle(long id, String newTitle);

    boolean changeDescription(long id, String newDescription);

    Optional<Product> findAnyByTitle (String title);

    List<Product> findAllByTitle (String title);
}
