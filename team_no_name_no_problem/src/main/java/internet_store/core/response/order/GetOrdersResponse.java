package internet_store.core.response.order;

import internet_store.core.domain.Order;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class GetOrdersResponse extends CoreResponse {

    private List<Order> orders;

    public GetOrdersResponse(List<Order> orders) {
        this.orders = orders;
    }

    public GetOrdersResponse(List<CoreError> errors, List<Order> orders) {
        super(errors);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
