package internet_store.application.core.responses.order;


import internet_store.application.core.domain.Order;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class AddOrderResponse extends CoreResponse {

    private Order newOrder;

    public AddOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddOrderResponse(Order newOrder) {
        this.newOrder = newOrder;
    }

    public Order getNewOrder() {
        return newOrder;
    }
}
