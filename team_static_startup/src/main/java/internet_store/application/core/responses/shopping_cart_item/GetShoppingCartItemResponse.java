package internet_store.application.core.responses.shopping_cart_item;

import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.dto.ShoppingCartItem;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class GetShoppingCartItemResponse extends CoreResponse {

    private ProductShoppingCart productShoppingCart;

    public GetShoppingCartItemResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetShoppingCartItemResponse(ProductShoppingCart productShoppingCart) {
        this.productShoppingCart = productShoppingCart;
    }

    public ProductShoppingCart getShoppingCartItem() {
        return productShoppingCart;
    }
}
