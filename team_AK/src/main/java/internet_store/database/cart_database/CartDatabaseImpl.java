package internet_store.database.cart_database;

import internet_store.core.domain.Cart;
import internet_store.database.interfaces.CartDatabase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class CartDatabaseImpl implements CartDatabase {
    private final List<Cart> productsInCart = new ArrayList<>();
    private final Cart EMPTY_OBJECT = null;
    private Long id = 1L;

    @Override
    public void addProductToCart(Cart cart) {
        cart.setId(id);
        id++;
        productsInCart.add(cart);
    }

    @Override
    public void deleteProductFromCart(Cart cart) {
        productsInCart.remove(cart);
    }

    @Override
    public List<Cart> getCart() {
        return productsInCart;
    }

    @Override
    public void updateCart(int index, Cart cart) {
        productsInCart.set(index, cart);
    }

    @Override
    public void showReport() {
        productsInCart.forEach(pr -> System.out.println("ID: " + pr.getId() + " " +
                "Title: " + pr.getProduct().getTitle() + " " + "Quantity: " + pr.getQuantity() + " " +
                "Price: " + pr.getProduct().getPrice() + " " + "Sum: " + pr.getSum()));
    }

    @Override
    public void clearCart() {
        productsInCart.clear();
    }

    @Override
    public boolean isIdExist(long id) {
        return productsInCart.stream().anyMatch(pr -> pr.getId() == id);
    }


    @Override
    public Cart findById(long id) {
        //noinspection ConstantConditions
        return productsInCart.stream().filter(pr -> pr.getId() == id).findFirst().orElse(EMPTY_OBJECT);
    }

    @Override
    public int findProductIndex(long id) {
        int LIST_FIRST_ELEMENT = 0;
        int NO_ID_FIND = -1;
        return IntStream.range(LIST_FIRST_ELEMENT, productsInCart.size())
                .filter(i -> productsInCart.get(i).getId() == id)
                .findFirst().orElse(NO_ID_FIND);
    }

    @Override
    public boolean isCartDatabaseEmpty() {
        return productsInCart.size() == 0;
    }

    @Override
    public Cart findByTitle(String title) {
        return productsInCart.stream().filter(p -> p.getProduct().getTitle().equals(title)).findFirst().orElse(null);
    }

    @Override
    public void  setId(long id) {
        this.id=id;
    }
}