package internet_store.database.cart_database;

import internet_store.core.domain.Product;

import java.util.List;

public interface InnerCartDatabase {
    void addProductToCart(Product product);

    void deleteProductFromCart(Product product);

    List<Product> getCart();

    void updateCart(int index, Product product);

    void showReport();

    boolean isIdExist(long id);

    void clearCart();

    Product findById(long id);

    int findProductIndex(long id);

    boolean isCartDatabaseEmpty();
}