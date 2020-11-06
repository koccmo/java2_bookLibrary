package internet_store.database;

import internet_store.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    public List <Product> getProductList();

    boolean save(Product product);

    boolean deleteById(long id);

    boolean printProducts();

    boolean changeTitle(long id, String newTitle);

    boolean changeDescription(long id, String newDescription);

    Optional<Product> findAnyByTitle (String title);

    List<Product> findAllByTitle (String title);
}
