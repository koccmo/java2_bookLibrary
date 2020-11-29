package internet_store.core.request.ordering;

import internet_store.core.service.ordering.OrderStatus;
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