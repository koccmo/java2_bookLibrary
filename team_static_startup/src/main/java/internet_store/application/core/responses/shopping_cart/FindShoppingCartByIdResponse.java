package internet_store.application.core.responses.shopping_cart;

import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class FindShoppingCartByIdResponse extends CoreResponse {

    private ShoppingCart shoppingCart;

    public FindShoppingCartByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindShoppingCartByIdResponse(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
