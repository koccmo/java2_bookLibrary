package internet_store.core.services.order;

import internet_store.core.requests.order.GetOrdersRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.order.GetOrdersResponse;
import internet_store.core.services.order.validators.GetOrdersRequestValidator;
import internet_store.database.order.OrderDatabase;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class GetOrdersService {

    @DIDependency private OrderDatabase orderDatabase;
    @DIDependency private GetOrdersRequestValidator getOrdersRequestValidator;

    public GetOrdersResponse execute (GetOrdersRequest getOrdersRequest) {
        List<CoreError> errors = getOrdersRequestValidator.validate(getOrdersRequest);

        if (!errors.isEmpty()) {
            return new GetOrdersResponse(errors, new ArrayList<>());
        }

        return new GetOrdersResponse(orderDatabase.getOrders());
    }
}
