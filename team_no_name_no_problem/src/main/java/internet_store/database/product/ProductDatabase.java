package internet_store.database.product;

import internet_store.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatabase {

    public List <Product> getProductList();

    boolean add(Product product);

    boolean deleteById(long id);

    Optional<Product> findById(Long id);

    boolean printProducts();

    void changeTitle(long id, String newTitle);

    void changeDescription(long id, String newDescription);

    Optional<Product> findAnyByTitle (String title);

    List<Product> findAllByTitle (String title);
}
