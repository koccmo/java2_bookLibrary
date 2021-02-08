package internet_store.application.core.responses.shopping_cart;

import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class AddShoppingCartResponse extends CoreResponse {

    private ShoppingCart newShoppingCart;

    public AddShoppingCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddShoppingCartResponse(ShoppingCart newShoppingCart) {
        this.newShoppingCart = newShoppingCart;
    }

    public ShoppingCart getNewShoppingCart() {
        return newShoppingCart;
    }
}
