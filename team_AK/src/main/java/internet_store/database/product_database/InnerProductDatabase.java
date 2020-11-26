package internet_store.database.product_database;

import internet_store.core.domain.Product;

public interface InnerProductDatabase {
    void addProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(int index, Product product);

    void showReport();

    boolean isIdExist(long id);

    Product findById(long id);

    Product findByTitle(String title);

    int findProductIndex(long id);

    boolean isEmpty();
}