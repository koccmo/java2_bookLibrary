package internet_store.database.interfaces;

import internet_store.core.domain.Product;

import java.util.List;

public interface ProductDatabase {
    void addProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(long index, Product product);

    void showReport();

    boolean isIdExist(long id);

    Product findById(long id);

    Product findByTitle(String title);

    int findProductIndex(long id);

    boolean isEmpty();

    boolean existsByTitle(String title);

    Long count();

    List<Product> getLimitsProductsRecords(int limit, int offset);

    void addAll(List<Product> allProducts);

    int recordsCount();

    void clear();

    void setId(long id);
}