package estore.database;

import java.util.List;
import estore.core.domain.Product;

public interface ProductRepository {

    List<Product> searchProductByName(String name);

    List<Product> searchProductByCategory(Long category);

    List<Product> searchProductById(Long id);

    boolean addNewProduct(Product product);

    int removeProductByName(String name);

    int removeProductById(Long id);

    List<Product> getDatabase();

    void updateProduct(Product product);

    int getDatabaseSize();

}
