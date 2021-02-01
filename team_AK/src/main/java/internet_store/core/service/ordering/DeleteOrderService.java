package internet_store.core.service.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Order;
import internet_store.core.request.ordering.DeleteOrderRequest;
import internet_store.core.response.ordering.DeleteOrderResponse;
import internet_store.core.validate.NumberValidator;
import internet_store.database.order_database.InnerOrderDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class DeleteOrderService {
    @Autowired
    InnerOrderDatabase orderDatabase;

    public DeleteOrderService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public DeleteOrderResponse execute(DeleteOrderRequest deleteOrderRequest) {
        NumberValidator<?> numberValidator = new NumberValidator<>
                (deleteOrderRequest.getId());

        List<CoreError> errors = numberValidator.validate();

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