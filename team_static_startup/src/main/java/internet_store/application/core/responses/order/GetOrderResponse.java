package internet_store.application.core.responses.order;

import internet_store.application.core.domain.Order;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class GetOrderResponse extends CoreResponse {

    private Order order;

    public GetOrderResponse(Order order) {
        this.order = order;
    }

    public GetOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public Order getOrder() {
        return order;
    }
}
