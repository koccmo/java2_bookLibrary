package internet_store.application.core.responses.shopping_cart;

import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class GetShoppingCartResponse extends CoreResponse {

    private ShoppingCart shoppingCart;

    public GetShoppingCartResponse(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public GetShoppingCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
