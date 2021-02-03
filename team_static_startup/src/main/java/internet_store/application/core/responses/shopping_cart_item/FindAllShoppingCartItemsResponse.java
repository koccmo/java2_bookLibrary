package internet_store.application.core.responses.shopping_cart_item;

import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class FindAllShoppingCartItemsResponse extends CoreResponse {

    private List<ProductShoppingCart> productShoppingCartList;

    public FindAllShoppingCartItemsResponse(List<CoreError> errors, List<ProductShoppingCart> productShoppingCartList) {
        super(errors);
        this.productShoppingCartList = productShoppingCartList;
    }

    public List<ProductShoppingCart> getProductShoppingCartList() {
        return productShoppingCartList;
    }

}
