package internet_store.database.cart_database;

import internet_store.core.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InnerCartDatabaseImpl implements InnerCartDatabase {
    private final List<Product> productsInCart = new ArrayList<>();
    private final Product EMPTY_OBJECT = null;

    @Override
    public void addProductToCart(Product product) {
        productsInCart.add(product);
    }

    @Override
    public void deleteProductFromCart(Product product) {
        productsInCart.remove(product);
    }

    @Override
    public List<Product> getCart() {
        return productsInCart;
    }

    @Override
    public void updateCart(int index, Product product) {
        productsInCart.set(index, product);
    }

    @Override
    public void showReport() {
        productsInCart.forEach(pr -> System.out.println("ID: " + pr.getId() + " " +
                "Title: " + pr.getTitle() + " " + "Description: " + pr.getDescription() + " " +
                "Quantity: " + pr.getQuantity() + " " + "Price: " + pr.getPrice() + " " + "Sum: " +
                pr.getSum()));
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
    public Product findById(long id) {
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
}