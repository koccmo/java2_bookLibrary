package lv.estore.app.core.database.shopping_cart;

import lv.estore.app.core.domain.Product;

import java.util.List;

public interface ShoppingCartRepository {
    void addProduct(Product product);
    void removeProduct(Product product);
    List<Product> getProducts();
}
