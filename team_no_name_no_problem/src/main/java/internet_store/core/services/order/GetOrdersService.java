package internet_store.core.services.order;

import internet_store.core.requests.order.GetOrdersRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.order.GetOrdersResponse;
import internet_store.core.services.order.validators.GetOrdersRequestValidator;
import internet_store.database.order.OrderDatabase;
import internet_store.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetOrdersService {

    @DIDependency private OrderDatabase orderDatabase;
    @DIDependency private GetOrdersRequestValidator getOrdersRequestValidator;

    public GetOrdersResponse execute (GetOrdersRequest getOrdersRequest) {
        List<CoreError> errors = getOrdersRequestValidator.validate(getOrdersRequest);

        if (!errors.isEmpty()) {
            return new GetOrdersResponse(errors, new ArrayList<>());
        }

        if (orderDatabase.getOrders().isEmpty()) {
            errors.add(new CoreError("order database", "Orders database is empty"));
            return new GetOrdersResponse(errors, new ArrayList<>());
        }
        return new GetOrdersResponse(orderDatabase.getOrders());
    }
}
