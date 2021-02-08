package internet_store.application.core.responses.shopping_cart_item;

import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindShoppingCartItemByIdResponse extends CoreResponse {

    private Optional<ProductShoppingCart> productShoppingCart;

    public FindShoppingCartItemByIdResponse(List<CoreError> errors) {
        super(errors);
    }


    public FindShoppingCartItemByIdResponse(Optional<ProductShoppingCart> productShoppingCart) {
        this.productShoppingCart = productShoppingCart;
    }

    public Optional<ProductShoppingCart> getProductShoppingCart() {
        return productShoppingCart;
    }
}
