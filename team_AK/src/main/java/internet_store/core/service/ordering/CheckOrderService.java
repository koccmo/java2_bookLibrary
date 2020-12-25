package internet_store.core.service.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.ordering.CheckOrderIdRequest;
import internet_store.core.response.ordering.CheckOrderIdResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.order_database.InnerOrderDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class CheckOrderService {
    @Autowired
    InnerOrderDatabase orderDatabase;

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