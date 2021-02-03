package internet_store.application.core.responses.shopping_cart_item;

import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class AddShoppingCartItemResponse extends CoreResponse {

    private Long cartItemId;

    public AddShoppingCartItemResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddShoppingCartItemResponse(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

}
