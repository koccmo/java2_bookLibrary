package internet_store.core.service.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Order;
import internet_store.core.request.ordering.DeleteOrderRequest;
import internet_store.core.response.ordering.DeleteOrderResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.order_database.InnerOrderDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.util.List;

@DIComponent
public class DeleteOrderService {
    @DIDependency
    InnerOrderDatabase orderDatabase;

    public DeleteOrderService() {
    }

    public DeleteOrderService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public DeleteOrderResponse execute(DeleteOrderRequest deleteOrderRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>
                (deleteOrderRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteOrderRequest.getId())) {
            Order deletedOrder = findProductById(deleteOrderRequest.getId());
            orderDatabase.deleteOrder(deletedOrder);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteOrderResponse(deleteOrderRequest.getId());
        }
        return new DeleteOrderResponse(errors);
    }

    private boolean isIdExist(long id) {
        return orderDatabase.isIdExist(id);
    }

    private Order findProductById(long id) {
        return orderDatabase.findById(id);
    }
}