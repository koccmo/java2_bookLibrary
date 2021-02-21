package internet_store.application.core.requests.shopping_cart_item;

public class DeleteShoppingCartItemRequest {

    private Long id;

    public DeleteShoppingCartItemRequest() { }

    public DeleteShoppingCartItemRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
