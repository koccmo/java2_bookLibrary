package internet_store.core.service.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.ordering.CheckOrderIdRequest;
import internet_store.core.response.ordering.CheckOrderIdResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.order_database.InnerOrderDatabase;

import java.util.List;

public class CheckOrderService {
    private final InnerOrderDatabase orderDatabase;

    public CheckOrderService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public CheckOrderIdResponse execute(CheckOrderIdRequest checkOrderIdRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>
                (checkOrderIdRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (!(isIdExist(checkOrderIdRequest.getId()))) {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }
        if (errors.isEmpty()) {
            return new CheckOrderIdResponse(checkOrderIdRequest.getId());
        }
        return new CheckOrderIdResponse(errors);

    }
    private boolean isIdExist(long id) {
        return orderDatabase.isIdExist(id);
    }
}