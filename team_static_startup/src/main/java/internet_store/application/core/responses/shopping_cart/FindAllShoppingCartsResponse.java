package internet_store.application.core.responses.shopping_cart;

import internet_store.application.core.domain.ShoppingCart;

import java.util.List;

public class FindAllShoppingCartsResponse {

    private List<ShoppingCart> shoppingCarts;

    public FindAllShoppingCartsResponse(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }
}
