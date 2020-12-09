package internet_store.core.services.order.validators;

import internet_store.core.requests.order.GetOrdersRequest;
import internet_store.core.response.CoreError;
import internet_store.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class GetOrdersRequestValidator {

    public List<CoreError> validate (GetOrdersRequest getOrdersRequest) {

        return new ArrayList<>();
    }
}
