package internet_store.database.product;

import internet_store.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    List <Product> getProducts();

    void add(Product product);

    boolean deleteById(Long id);

    boolean deleteAllByTitle(String title);

    boolean deleteAllByDescription(String description);

    boolean deleteAllByPriceRange(Integer startPrice, Integer endPrice);

    Optional<Product> findById(Long id);

    void changeTitle(Long id, String newTitle);

    void changeDescription(Long id, String newDescription);

    void changePrice(Long id, Integer newPrice);

    List<Product> findAllByTitle (String title);

    List<Product> findAllByPriceRange(Integer startPrice, Integer endPrice);

    List<Product> findAllByDescription (String description);

    List<Product> findAllByTitleAndDescription (String title, String description);

    boolean containsProduct (Product product);

    boolean containsId (Long id);

    boolean containsTitle (String title);

    boolean containsDescription (String description);

}
