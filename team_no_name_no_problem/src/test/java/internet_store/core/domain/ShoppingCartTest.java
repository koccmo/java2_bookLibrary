package internet_store.core.domain;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ShoppingCartTest {

    @Test
    public void shoppingCartAddProduct(){

        ShoppingCart shoppingCart = new ShoppingCart();

        Product laptopDell = new Product("Laptop", "Dell", 400);
        Product refrigerator = new Product("Refrigerator", "Electrolux", 300);
        Product tv = new Product("TV", "Radiotehnika", 3);
        Product gpsNavigator = new Product("GPS Navigator", "Car accessories", 45);

        shoppingCart.addProduct(laptopDell,1);
        shoppingCart.addProduct(refrigerator,2);
        shoppingCart.addProduct(tv,1);
        shoppingCart.addProduct(gpsNavigator,5);


        Map<Product, Integer> resultOfShoppingCart = shoppingCart.getShoppingCart();

        assertTrue(resultOfShoppingCart.containsKey(laptopDell));
        assertTrue(resultOfShoppingCart.keySet().contains(refrigerator));
    }

}