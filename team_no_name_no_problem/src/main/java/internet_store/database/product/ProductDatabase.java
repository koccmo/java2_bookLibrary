package internet_store.database.product;

import internet_store.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    List <Product> getProductList();

    void addProduct(Product product);

    void deleteById(long id);

    void changeTitle(long id, String newTitle);

    void changeDescription(long id, String newDescription);

    Optional<Product> findAnyByTitle (String title);

    List<Product> findAllByTitle (String title);
}
