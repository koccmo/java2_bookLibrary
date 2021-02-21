package internet_store.application.core.responses.shopping_cart_item;

import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class UpdateShoppingCartItemResponse extends CoreResponse {

    private ProductShoppingCart updatedCartProduct;

    public UpdateShoppingCartItemResponse(ProductShoppingCart updatedCartProduct) {
        this.updatedCartProduct = updatedCartProduct;
    }

    public UpdateShoppingCartItemResponse(List<CoreError> errors) {
        super(errors);
    }

    public ProductShoppingCart getUpdatedCartProduct() {
        return updatedCartProduct;
    }

}
