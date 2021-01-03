package internet_store.database.product;

import internet_store.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    List <Product> getProducts();

    void add(Product product);

    void deleteById(Long id);

    Optional<Product> findById(Long id);

    void changeTitle(Long id, String newTitle);

    void changeDescription(Long id, String newDescription);

    void changePrice(Long id, Integer newPrice);

    List<Product> findAllByTitle (String title);

    List<Product> findAllByPrice (Integer startPrice, Integer endPrice);

    List<Product> findAllByDescription (String description);

    List<Product> findAllByTitleAndDescription (String title, String description);

    boolean containsProduct (Product product);

    boolean containsId (Long id);
}
