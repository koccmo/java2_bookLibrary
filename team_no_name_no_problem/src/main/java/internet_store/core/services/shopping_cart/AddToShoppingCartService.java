package internet_store.core.services.shopping_cart;

import internet_store.core.domain.ShoppingCart;
import internet_store.dependency_injection.DIComponent;


public class AddToShoppingCartService {

    private final ShoppingCart shoppingCart;

    public AddToShoppingCartService(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
