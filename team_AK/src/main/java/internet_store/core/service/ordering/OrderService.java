package internet_store.core.service.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.client_database.InnerClientDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class OrderService {
    @Autowired
    InnerClientDatabase clientDatabase;
    @Autowired
    InnerCartDatabase cartDatabase;

    public OrderService(InnerClientDatabase clientDatabase, InnerCartDatabase cartDatabase) {
        this.clientDatabase = clientDatabase;
        this.cartDatabase = cartDatabase;
    }

    public OrderResponse execute(OrderRequest orderRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(orderRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (cartDatabase.isCartDatabaseEmpty()) {
            errors.add(new CoreError("Cart error ", "cart empty"));
        }

        if (!(isIdExist(orderRequest.getId()))) {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new OrderResponse(orderRequest.getId());
        }
        return new OrderResponse(errors);
    }

    private boolean isIdExist(long id) {
        return clientDatabase.isIdExist(id);
    }
}