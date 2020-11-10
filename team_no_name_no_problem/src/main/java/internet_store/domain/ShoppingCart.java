package internet_store.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShoppingCart {

    Product product;
    Integer price;

    Map <Product, Integer> shoppingCart = new HashMap <>();


}
