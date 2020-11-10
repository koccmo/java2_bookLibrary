package internet_store.domain;

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


    /*
    private String itemName;
    private int itemCost;
    private int quantity;

    public ShoppingCart(String itemName, int itemCost, int quantity) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.quantity = quantity;
        // что еще можно сюда добавить?
    }*/


}
