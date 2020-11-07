package estore.database;

import estore.domain.Product;

import java.util.List;

public interface ProductDataBase {

    List<Product> searchProductByName(String name);

    boolean addNewProduct(Product product);

    int removeProductByName(String name);

    int removeProductById(Long id);

    List<Product> getDatabase();

    int getDatabaseSize();

}
