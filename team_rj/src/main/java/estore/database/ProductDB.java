package estore.database;

import java.util.List;
import estore.domain.Product;

public interface ProductDB {

    List<Product> searchProductByName(String name);

    List<Product> searchProductByCategory(String category);

    boolean addNewProduct(Product product);

    int removeProductByName(String name);

    int removeProductById(Long id);

    List<Product> getDatabase();

    int getDatabaseSize();

}
