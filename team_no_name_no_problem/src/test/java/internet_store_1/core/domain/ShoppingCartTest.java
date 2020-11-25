package internet_store_1.core.domain;

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
        assertEquals(resultOfShoppingCart.size(),4);
        assertFalse(resultOfShoppingCart.size() == 5);
    }

    @Test
    public void shoppingCartDeleteProduct() {

        ShoppingCart shoppingCart = new ShoppingCart();

        Product mobilePhone = new Product("Samsung", "Galaxy S7", 600);
        Product backPack = new Product("ECCO", "For travelling", 90);

        shoppingCart.addProduct(mobilePhone,1);
        shoppingCart.addProduct(backPack,1);
        shoppingCart.deleteProduct(backPack);

        Map<Product, Integer> resultOfShoppingCart = shoppingCart.getShoppingCart();

        assertTrue(resultOfShoppingCart.size() == 1);
        assertTrue(resultOfShoppingCart.containsKey(mobilePhone));
        assertFalse(resultOfShoppingCart.size() == 2);
        assertFalse(resultOfShoppingCart.containsKey(backPack));
    }

}