package lesson_3.database.product_database;

import lesson_3.core.domain.Product;

import java.util.List;

public interface InnerProductDatabase {
    void addProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(int index, Product product);

    void showReport();

    List<Product> getAllProducts();
}