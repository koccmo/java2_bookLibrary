package internet_store.application.core.requests.order;

import java.time.LocalDateTime;
import java.util.Date;

public class AddOrderRequest {

    private Long shoppingCartId;
    private LocalDateTime orderDate;
    private boolean isActive;

    public AddOrderRequest(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }
}
