package internet_store.application.core.responses.shopping_cart_item;

import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class DeleteShoppingCartItemResponse extends CoreResponse {

    private ProductShoppingCart deletedProduct;

    public DeleteShoppingCartItemResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteShoppingCartItemResponse(ProductShoppingCart deletedProduct) {
        this.deletedProduct = deletedProduct;
    }

    public ProductShoppingCart getDeletedProduct() {
        return deletedProduct;
    }

}
