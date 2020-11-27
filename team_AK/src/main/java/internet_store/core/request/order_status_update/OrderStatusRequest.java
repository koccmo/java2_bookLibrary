package internet_store.core.request.order_status_update;

import internet_store.ordering.OrderStatus;
import lombok.Getter;

public class OrderStatusRequest {
    @Getter
    private final OrderStatus orderStatus;
    @Getter
    private final long orderId;

    public OrderStatusRequest(OrderStatus orderStatus, long orderId) {
        this.orderStatus = orderStatus;
        this.orderId = orderId;
    }
}