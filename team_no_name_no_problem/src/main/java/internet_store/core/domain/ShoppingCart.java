package internet_store.core.domain;

import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {

    Product product;
    Integer quantity;

    Map <Product, Integer> shoppingCart = new HashMap <>();

    void addProduct(Product product, Integer quantity){
        shoppingCart.put(product, quantity);
    }

    void deleteProduct(Product product){
        if (shoppingCart.keySet().contains(product)){
            shoppingCart.remove(product);
        }
    }

    void changeProductQuantity(Product product, int quantity){
        shoppingCart.put(product, quantity);
    }

}
