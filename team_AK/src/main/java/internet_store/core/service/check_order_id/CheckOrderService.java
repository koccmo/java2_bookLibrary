package internet_store.core.service.check_order_id;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.check_order_id.CheckOrderIdRequest;
import internet_store.core.response.check_order_id.CheckOrderIdResponse;
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