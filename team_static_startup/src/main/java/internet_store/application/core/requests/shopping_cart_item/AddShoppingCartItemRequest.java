package internet_store.application.core.requests.shopping_cart_item;

public class AddShoppingCartItemRequest {

    private Long shoppingCartId;
    private Long productId;
    private Long quantity;

    public AddShoppingCartItemRequest(Long shoppingCartId, Long productId, Long quantity) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
