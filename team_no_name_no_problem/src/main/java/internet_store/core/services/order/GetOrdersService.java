package internet_store.core.services.order;

import internet_store.core.domain.Order;
import internet_store.core.requests.order.GetOrdersRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.order.GetOrdersResponse;
import internet_store.core.services.order.validators.GetOrdersRequestValidator;
import internet_store.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class GetOrdersService {

    @Autowired
    private OrderDatabase orderDatabase;
    @Autowired
    private GetOrdersRequestValidator getOrdersRequestValidator;

    public GetOrdersResponse execute (GetOrdersRequest getOrdersRequest) {
        List<CoreError> errors = getOrdersRequestValidator.validate(getOrdersRequest);

        if (orderDatabase.getOrders().isEmpty()) {
            errors.add(new CoreError("order database", "Orders database is empty"));
            return new GetOrdersResponse(errors, new ArrayList<>());
        }
        List<Order> orders = orderDatabase.getOrders();
        return new GetOrdersResponse(orders);
    }

}