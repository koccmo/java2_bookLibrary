package internet_store.application.core.requests.shopping_cart_item;

public class GetShoppingCartItemRequest {

    private Long id;

    public GetShoppingCartItemRequest() {
    }

    public GetShoppingCartItemRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
