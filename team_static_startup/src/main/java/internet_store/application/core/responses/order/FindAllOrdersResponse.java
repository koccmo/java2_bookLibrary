package internet_store.application.core.responses.order;

import internet_store.application.core.domain.Order;

import java.util.List;

public class FindAllOrdersResponse {

    private List<Order> orders;

    public FindAllOrdersResponse(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
