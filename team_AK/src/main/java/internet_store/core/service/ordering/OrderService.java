package internet_store.core.service.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.core.validate.NumberValidator;
import internet_store.database.interfaces.CartDatabase;
import internet_store.database.interfaces.ClientDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class OrderService {
    @Autowired
    ClientDatabase clientDatabase;
    @Autowired
    CartDatabase cartDatabase;

    public OrderService(ClientDatabase clientDatabase, CartDatabase cartDatabase) {
        this.clientDatabase = clientDatabase;
        this.cartDatabase = cartDatabase;
    }

    public OrderResponse execute(OrderRequest orderRequest) {
        NumberValidator<?> numberValidator = new NumberValidator<>(orderRequest.getId());

        List<CoreError> errors = numberValidator.validate();

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