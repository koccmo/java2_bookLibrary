package internet_store.application.core.requests.shopping_cart_item;

public class UpdateShoppingCartItemRequest {

    private Long id;
    private Long quantity;

    public UpdateShoppingCartItemRequest() { }

    public UpdateShoppingCartItemRequest(Long id, Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
