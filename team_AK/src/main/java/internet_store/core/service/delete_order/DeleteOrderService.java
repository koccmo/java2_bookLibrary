package internet_store.core.service.delete_order;

import internet_store.ProductListApplication;
import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Order;
import internet_store.core.request.delete_order.DeleteOrderRequest;
import internet_store.core.response.delete_order.DeleteOrderResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.order_database.InnerOrderDatabase;

import java.util.List;

public class DeleteOrderService {
    private final InnerOrderDatabase orderDatabase;

    public DeleteOrderService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public DeleteOrderResponse execute(DeleteOrderRequest deleteOrderRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>
                (deleteOrderRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteOrderRequest.getId())) {
            Order deletedOrder = findProductById(deleteOrderRequest.getId());
            ProductListApplication.orderDatabase.deleteOrder(deletedOrder);
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