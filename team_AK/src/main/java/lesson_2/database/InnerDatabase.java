package lesson_2.database;

import internet_store.domain.Product;

import java.util.List;

public interface InnerDatabase {
    void addProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(int index, Product product);

    void showReport();

    List<Product> getAllProducts();
}