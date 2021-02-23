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

    boolean deleteAllByTitleAndDescription(String title, String description);

    boolean deleteAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice);

    boolean deleteAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice);

    boolean deleteAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice);

    Optional<Product> searchById(Long id);

    void changeTitle(Long id, String newTitle);

    void changeDescription(Long id, String newDescription);

    void changePrice(Long id, Integer newPrice);

    List<Product> searchAllByTitle(String title);

    List<Product> searchAllByDescription(String description);

    List<Product> searchAllByPriceRange(Integer startPrice, Integer endPrice);

    List<Product> searchAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice);

    List<Product> searchAllByTitleAndDescription(String title, String description);

    List<Product> searchAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice);

    List<Product> searchAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice);

    boolean containsProduct (Product product);

    boolean containsId(Long id);

    boolean containsTitle(String title);

    boolean containsDescription(String description);

    boolean containsPrice(Integer price);

    boolean containsTitleAndDescription(String title, String description);

}
