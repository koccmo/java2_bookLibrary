package internet_store.application.core.responses.order;

import internet_store.application.core.domain.Order;
import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class FindOrderByIdResponse extends CoreResponse {

    private Order order;

    public FindOrderByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindOrderByIdResponse(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
