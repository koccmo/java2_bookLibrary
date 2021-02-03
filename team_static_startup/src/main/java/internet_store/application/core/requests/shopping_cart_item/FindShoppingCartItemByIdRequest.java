package internet_store.application.core.requests.shopping_cart_item;

public class FindShoppingCartItemByIdRequest {

    private Long shoppingCartItemId;

    public FindShoppingCartItemByIdRequest(Long shoppingCartItemId) {
        this.shoppingCartItemId = shoppingCartItemId;
    }

    public Long getShoppingCartItemId() {
        return shoppingCartItemId;
    }

}
