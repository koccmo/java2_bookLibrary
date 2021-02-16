package internet_store.application.core.responses.shopping_cart_item;

import internet_store.application.core.dto.ShoppingCartItem;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class GetShoppingCartItemResponse extends CoreResponse {

    private ShoppingCartItem shoppingCartItem;

    public GetShoppingCartItemResponse(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    public GetShoppingCartItemResponse(List<CoreError> errors) {
        super(errors);
    }

    public ShoppingCartItem getShoppingCartItem() {
        return shoppingCartItem;
    }
}
