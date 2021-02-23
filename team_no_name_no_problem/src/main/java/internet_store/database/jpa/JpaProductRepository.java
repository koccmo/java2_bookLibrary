package internet_store.database.jpa;

import internet_store.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProducts();

    List<Product> add(Product product);

    void deleteById(Long id);

    List<Product> deleteAllByTitle(String title);

    List<Product> deleteAllByDescription(String description);

    List<Product> deleteAllByPriceRange(Integer startPrice, Integer endPrice);

    List<Product> deleteAllByTitleAndDescription(String title, String description);

    List<Product> deleteAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice);

    List<Product> deleteAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice);

    List<Product> deleteAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice);

    Optional<Product> searchById(Long id);

    List<Product> changeTitle(Long id, String newTitle);

    List<Product> changeDescription(Long id, String newDescription);

    List<Product> changePrice(Long id, Integer newPrice);

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
