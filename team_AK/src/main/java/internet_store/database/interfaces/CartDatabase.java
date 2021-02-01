package internet_store.database.interfaces;

import internet_store.core.domain.Cart;

import java.util.List;

public interface CartDatabase {
    void addProductToCart(Cart cart);

    void deleteProductFromCart(Cart cart);

    List<Cart> getCart();

    void updateCart(int index, Cart cart);

    void showReport();

    boolean isIdExist(long id);

    void clearCart();

    Cart findById(long id);

    int findProductIndex(long id);

    boolean isCartDatabaseEmpty();

    Cart findByTitle(String title);

    void  setId(long id);
}