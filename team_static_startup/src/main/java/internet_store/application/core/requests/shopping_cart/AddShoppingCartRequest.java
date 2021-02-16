package internet_store.application.core.requests.shopping_cart;

public class AddShoppingCartRequest {

    private Long customerId;

    public AddShoppingCartRequest() {
    }

    public AddShoppingCartRequest(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
